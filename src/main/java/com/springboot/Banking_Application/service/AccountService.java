package com.springboot.Banking_Application.service;

import com.springboot.Banking_Application.dto.AccountDto;

import java.util.List;

public interface AccountService {

    List<AccountDto> getAllAccounts();

    AccountDto getAccountById(Long id);

    AccountDto createAccount(AccountDto accountDTO);

    AccountDto depositBalance(Long id, double amount);

    AccountDto withdrawBalance(Long id, double amount);

    void deleteAccount(Long id);
}