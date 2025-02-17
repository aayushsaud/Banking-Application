package com.springboot.Banking_Application.service.impl;

import com.springboot.Banking_Application.dto.AccountDTO;
import com.springboot.Banking_Application.entity.Account;
import com.springboot.Banking_Application.mapper.AccountMapper;
import com.springboot.Banking_Application.repository.AccountRepository;
import com.springboot.Banking_Application.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = AccountMapper.mapToAccount(accountDTO);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }
}