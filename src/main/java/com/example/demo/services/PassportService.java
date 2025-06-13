package com.example.demo.services;

import com.example.demo.DTO.PassportDTO;

import java.util.List;

public interface PassportService {
    PassportDTO create(PassportDTO user);
    PassportDTO findById(Long id);
    List<PassportDTO> getAll();
    PassportDTO update(Long id, PassportDTO userDTO);
    void delete(Long id);
}
