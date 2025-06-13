package com.example.demo.services.impl;

import com.example.demo.DTO.AccountDTO;
import com.example.demo.entity.Account;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.services.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;


    @Override
    public AccountDTO create(AccountDTO user) {
        Account account = accountRepository.save(Account.builder().title(user.title()).build());
        return AccountDTO.builder().title(account.getTitle()).build();
    }

    public AccountDTO findById(Long id) {
        return AccountDTO.builder()
                .title(accountRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("account not found"))
                        .getTitle()).build();
    }

    @Override
    public List<AccountDTO> getAll() {
        return accountRepository.findAll().stream()
                .map(account -> AccountDTO.builder()
                        .title(account.getTitle()).build())
                .collect(Collectors.toList());
    }

    @Override
    public AccountDTO update(Long id, AccountDTO accountDTO) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new NoSuchElementException("account not found"));
        account.setTitle(accountDTO.title());
        accountRepository.save(account);
        return AccountDTO.builder().title(account.getTitle()).build();
    }


    public void delete(Long id) {
        accountRepository.deleteById(id);
    }


}
