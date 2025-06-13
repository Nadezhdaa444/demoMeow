package com.example.demo.services;

import com.example.demo.DTO.AccountDTO;

import java.util.List;

public interface AccountService {

    AccountDTO create(AccountDTO user);
    AccountDTO findById(Long id);
    List<AccountDTO> getAll();
    AccountDTO update(Long id, AccountDTO userDTO);
    void delete(Long id);
}
