package com.springboot.Banking_Application.service;

import com.springboot.Banking_Application.dto.AccountDTO;

import java.util.List;

public interface AccountService {

    List<AccountDTO> getAllAccounts();

    AccountDTO getAccountById(Long id);

    AccountDTO createAccount(AccountDTO accountDTO);

    AccountDTO depositBalance(Long id, double amount);

    AccountDTO withdrawBalance(Long id, double amount);

    void deleteAccount(Long id);
}