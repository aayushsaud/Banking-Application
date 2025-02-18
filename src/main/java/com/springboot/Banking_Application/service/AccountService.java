package com.springboot.Banking_Application.service;

import com.springboot.Banking_Application.dto.AccountDTO;
public interface AccountService {

    AccountDTO createAccount(AccountDTO accountDTO);

    AccountDTO getAccountById(Long id);

    AccountDTO depositBalance(Long id, double amount);
}