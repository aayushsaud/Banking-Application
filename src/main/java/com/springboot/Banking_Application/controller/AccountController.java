package com.springboot.Banking_Application.controller;

import com.springboot.Banking_Application.dto.AccountDto;
import com.springboot.Banking_Application.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts () {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AccountDto> getAccountById (@PathVariable Long id) {
        return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount (@RequestBody AccountDto accountDto) {
        AccountDto account = accountService.createAccount(accountDto);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PutMapping("/deposit/{id}")
    public ResponseEntity<?> depositBalance (@PathVariable Long id, @RequestBody Map<String, Double> request) {
        return new ResponseEntity<>(accountService.depositBalance(id, request.get("amount")), HttpStatus.CREATED);
    }

    @PutMapping("/withdraw/{id}")
    public ResponseEntity<?> withdrawBalance (@PathVariable Long id, @RequestBody Map<String, Double> request) {
        return new ResponseEntity<>(accountService.withdrawBalance(id, request.get("amount")), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount (@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}