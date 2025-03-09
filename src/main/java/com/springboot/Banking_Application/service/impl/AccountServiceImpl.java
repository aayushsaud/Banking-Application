package com.springboot.Banking_Application.service.impl;

import com.springboot.Banking_Application.dto.AccountDto;
import com.springboot.Banking_Application.entity.Account;
import com.springboot.Banking_Application.exception.AccountNotFoundException;
import com.springboot.Banking_Application.exception.InsufficientBalanceException;
import com.springboot.Banking_Application.mapper.AccountMapper;
import com.springboot.Banking_Application.repository.AccountRepository;
import com.springboot.Banking_Application.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accountsList = accountRepository.findAll();
        return accountsList.stream().map(AccountMapper::mapToAccountDto).toList();
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + id));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto depositBalance(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + id));
        account.setBalance(account.getBalance() + amount);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdrawBalance(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + id));
        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance in account with ID:" + id);
        }
        account.setBalance(account.getBalance() - amount);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + id));
        accountRepository.deleteById(id);
    }
}