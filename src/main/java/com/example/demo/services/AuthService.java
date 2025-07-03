package com.example.demo.services;

import com.example.demo.DTO.JwtResponse;
import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.RefreshRequest;
import com.example.demo.DTO.RegisterRequest;
import org.apache.commons.lang3.LongRange;

public interface AuthService {
   void register(RegisterRequest reginRequest);
   JwtResponse login(LoginRequest request);
   JwtResponse refreshToken(RefreshRequest request);


}
