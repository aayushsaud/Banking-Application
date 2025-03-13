package com.springboot.Banking_Application.controller;

import com.springboot.Banking_Application.dto.AccountDto;
import com.springboot.Banking_Application.dto.UserDto;
import com.springboot.Banking_Application.entity.Account;
import com.springboot.Banking_Application.entity.User;
import com.springboot.Banking_Application.mapper.AccountMapper;
import com.springboot.Banking_Application.mapper.UserMapper;
import com.springboot.Banking_Application.service.AccountService;
import com.springboot.Banking_Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        UserDto userDto = userService.findByUserName(userName);
        User user = UserMapper.mapToUser(userDto);

        List<Account> account = user.getAccounts();
        if (account.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<AccountDto> accountDto = account.stream()
                .map(AccountMapper::mapToAccountDto)
                .toList();

        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount (@RequestBody AccountDto accountDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        AccountDto account = accountService.createAccount(accountDto, userName);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PutMapping("/deposit/{id}")
    public ResponseEntity<?> depositBalance (@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return new ResponseEntity<>(accountService.depositBalance(id, userName, request.get("amount")), HttpStatus.CREATED);
    }

    @PutMapping("/withdraw/{id}")
    public ResponseEntity<?> withdrawBalance (@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return new ResponseEntity<>(accountService.withdrawBalance(id, userName, request.get("amount")), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount (@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        accountService.deleteAccount(id, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}