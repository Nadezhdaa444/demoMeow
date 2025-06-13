package com.example.demo.services.impl;

import com.example.demo.DTO.PassportDTO;
import com.example.demo.entity.Passport;
import com.example.demo.repositories.PassportRepository;
import com.example.demo.services.PassportService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional

@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {
    private final PassportRepository passportRepository;

    public PassportDTO create(PassportDTO passportDTO){
        Passport passport= passportRepository.save(Passport.builder().number(passportDTO.number()).build());
        return PassportDTO.builder().number(passport.getNumber()).build();

    }

    public PassportDTO findById(Long id) {
        return PassportDTO.builder()
                .number(passportRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("passport not found"))
                        .getNumber()).build();
    }

    public List<PassportDTO> getAll(){
        return passportRepository.findAll().stream()
                .map(passport -> PassportDTO.builder()
                        .number(passport.getNumber()).build())
                .collect(Collectors.toList());
    }

    @Override
    public PassportDTO update(Long id, PassportDTO passportDTO) {
        Passport passport = passportRepository.findById(id).orElseThrow(() -> new NoSuchElementException("passport not found"));
        passport.setNumber(passportDTO.number());
        passportRepository.save(passport);
        return PassportDTO.builder().number(passport.getNumber()).build();    }


    public void delete(Long id){
        passportRepository.deleteById(id);
    }

}
