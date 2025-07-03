package com.example.demo.services.impl;

import com.example.demo.DTO.JwtResponse;
import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.RefreshRequest;
import com.example.demo.DTO.RegisterRequest;
import com.example.demo.config.JwtUtils;
import com.example.demo.entity.Credentials;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repositories.CredentialsRepository;

import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;

import com.example.demo.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final CredentialsRepository credentialsRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public void register(RegisterRequest registerRequest) {
        User user = new User();
        user.setName(registerRequest.name());
        user.setAge(registerRequest.age());

        Role role = roleRepository.findByName("ROLE_USER").orElseThrow(()->new RuntimeException("Role not found"));

        Credentials credential = new Credentials();
        credential.setUsername(registerRequest.name());
        credential.setPassword(passwordEncoder.encode(registerRequest.password()));
        credential.setRole(role);
        credential.setUser(user);

        credentialsRepository.save(credential);
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        Credentials credentials = credentialsRepository.findByUserName(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!passwordEncoder.matches(request.password(), credentials.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        String access = jwtUtils.generateAccessToken(request.username());
        String refresh = jwtUtils.generateRefreshToken(request.username());
        return new JwtResponse(access, refresh);
    }

    @Override
    public JwtResponse refreshToken(RefreshRequest request) {
        if (!jwtUtils.isTokenValid(request.refreshToken())) {
            throw new RuntimeException("Invalid refresh token");
        }
        String username = jwtUtils.extractUsername(request.refreshToken());
        String newAccess = jwtUtils.generateAccessToken(username);
        return new JwtResponse(newAccess, request.refreshToken());
    }
}