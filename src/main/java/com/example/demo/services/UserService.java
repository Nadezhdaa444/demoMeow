package com.example.demo.services;

import com.example.demo.DTO.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO create(UserDTO user);
    UserDTO findById(Long id);
    List<UserDTO> getAll();
    UserDTO update(Long id, UserDTO userDTO);
    void delete(Long id);
}
