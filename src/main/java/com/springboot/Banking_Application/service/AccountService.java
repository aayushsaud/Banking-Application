package com.springboot.Banking_Application.service;

import com.springboot.Banking_Application.dto.AccountDto;

import java.util.List;

public interface AccountService {

    List<AccountDto> getAllAccounts(String userName);

    AccountDto createAccount(AccountDto accountDTO, String userName);

    AccountDto depositBalance(Long id, String userName, double amount);

    AccountDto withdrawBalance(Long id, String userName, double amount);

    void deleteAccount(Long id, String userName);
}