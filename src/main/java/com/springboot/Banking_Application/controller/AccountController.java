package com.springboot.Banking_Application.controller;

import com.springboot.Banking_Application.dto.AccountDTO;
import com.springboot.Banking_Application.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById (@PathVariable Long id) {
        return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createAccount (@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
    }

    @PutMapping("/deposit/{id}")
    public ResponseEntity<?> depositBalance (@PathVariable Long id, @RequestBody Map<String, Double> request) {
        return new ResponseEntity<>(accountService.depositBalance(id, request.get("amount")), HttpStatus.CREATED);
    }
}