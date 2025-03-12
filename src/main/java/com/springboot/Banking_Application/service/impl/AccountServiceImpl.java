package com.springboot.Banking_Application.service.impl;

import com.springboot.Banking_Application.dto.AccountDto;
import com.springboot.Banking_Application.dto.UserDto;
import com.springboot.Banking_Application.entity.Account;
import com.springboot.Banking_Application.entity.User;
import com.springboot.Banking_Application.exception.AccountNotFoundException;
import com.springboot.Banking_Application.exception.InsufficientBalanceException;
import com.springboot.Banking_Application.mapper.AccountMapper;
import com.springboot.Banking_Application.mapper.UserMapper;
import com.springboot.Banking_Application.repository.AccountRepository;
import com.springboot.Banking_Application.service.AccountService;
import com.springboot.Banking_Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    UserService userService;

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
        UserDto userDto = userService.findUserByUserName(accountDto.getAccountHolderName());
        User user = UserMapper.mapToUser(userDto);
        Account account = AccountMapper.mapToAccount(accountDto);
        account.setUser(user);
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
            throw new InsufficientBalanceException("Insufficient balance in account with ID: " + id);
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