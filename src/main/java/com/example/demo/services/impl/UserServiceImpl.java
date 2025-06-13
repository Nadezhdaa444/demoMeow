package com.example.demo.services.impl;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.Hobby;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repositories.HobbyRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final HobbyRepository hobbyRepository;


    @Override
    public UserDTO create(UserDTO user) {
     Set<Hobby> hobbies = user.hobbies().stream()
             .map(type -> hobbyRepository.findByType(type)
                     .orElseGet(()-> hobbyRepository
                             .save(Hobby.builder().type(type).build()))).collect(Collectors.toSet());
     User userToSave = UserMapper.toEntity(user, List.copyOf(hobbies));
     return UserMapper.toDTO(userRepository.save(userToSave));
    }

    @Override
    public UserDTO findById(Long id) {
        return userRepository.findById(id).map(UserMapper::toDTO).orElseThrow(() -> new NoSuchElementException("not found user"));
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("USER not found"));
        existingUser.setName(userDTO.name());
        existingUser.setAge(userDTO.age());
        return UserMapper.toDTO(userRepository.save(existingUser));
    }
    @Override
    public List<UserDTO> getAll(){
        return userRepository.findAll().stream().map(UserMapper::toDTO).toList();
    }
    @Override
    public void delete(Long id) {
    userRepository.deleteById(id);
    }
}
