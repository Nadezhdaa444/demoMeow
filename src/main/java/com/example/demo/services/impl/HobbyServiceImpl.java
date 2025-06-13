package com.example.demo.services.impl;

import com.example.demo.DTO.HobbyDTO;
import com.example.demo.entity.Hobby;
import com.example.demo.repositories.HobbyRepository;
import com.example.demo.services.HobbyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HobbyServiceImpl implements HobbyService {

    private final HobbyRepository hobbyRepository;

    @Override
    public HobbyDTO create(HobbyDTO user) {
        Hobby hobby = hobbyRepository.save(Hobby.builder().type(user.type()).build());
        return HobbyDTO.builder().type(hobby.getType()).build();
    }

    @Override
    public HobbyDTO findById(Long id) {
        return HobbyDTO.builder()
                .type(hobbyRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("account not found"))
                        .getType()).build();    }

    @Override
    public List<HobbyDTO> getAll() {
        return hobbyRepository.findAll().stream()
                .map(hobby -> HobbyDTO.builder()
                        .type(hobby.getType()).build())
                .collect(Collectors.toList());    }

    @Override
    public HobbyDTO update(Long id, HobbyDTO userDTO) {
        Hobby hobby = hobbyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("passport not found"));
        hobby.setType(hobby.getType());
        hobby.setId(hobby.getId());
        hobby.setUsers(hobby.getUsers());
        hobbyRepository.save(hobby);
        return HobbyDTO.builder().type(hobby.getType()).build();      }

    @Override
    public void delete(Long id) {
        hobbyRepository.deleteById(id);

    }
}
