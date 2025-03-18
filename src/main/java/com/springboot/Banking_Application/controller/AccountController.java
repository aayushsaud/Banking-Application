package com.springboot.Banking_Application.controller;

import com.springboot.Banking_Application.dto.AccountDto;
import com.springboot.Banking_Application.dto.UserDto;
import com.springboot.Banking_Application.elastic.ElasticAccount;
import com.springboot.Banking_Application.elastic.ElasticAccountMapper;
import com.springboot.Banking_Application.entity.Account;
import com.springboot.Banking_Application.entity.User;
import com.springboot.Banking_Application.mapper.AccountMapper;
import com.springboot.Banking_Application.mapper.UserMapper;
import com.springboot.Banking_Application.service.AccountService;
import com.springboot.Banking_Application.service.UserService;
import com.springboot.Banking_Application.service.impl.ElasticAccountService;
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

    @Autowired
    ElasticAccountService elasticAccountService;

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
        AccountDto accountDto1 = accountService.createAccount(accountDto, userName);

        Long userId = userService.findByUserName(userName).getId();
        ElasticAccount elasticAccount = ElasticAccountMapper.mapToElasticAccount(accountDto1, userId);
        elasticAccountService.save(elasticAccount);

        return new ResponseEntity<>(accountDto1, HttpStatus.CREATED);
    }

    @PutMapping("/deposit/{id}")
    public ResponseEntity<?> depositBalance (@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        AccountDto accountDto = accountService.depositBalance(id, userName, request.get("amount"));

        Long userId = userService.findByUserName(userName).getId();
        ElasticAccount elasticAccount = ElasticAccountMapper.mapToElasticAccount(accountDto, userId);
        elasticAccountService.save(elasticAccount);

        return new ResponseEntity<>(accountDto, HttpStatus.CREATED);
    }

    @PutMapping("/withdraw/{id}")
    public ResponseEntity<?> withdrawBalance (@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        AccountDto accountDto = accountService.depositBalance(id, userName, request.get("amount"));

        Long userId = userService.findByUserName(userName).getId();
        ElasticAccount elasticAccount = ElasticAccountMapper.mapToElasticAccount(accountDto, userId);
        elasticAccountService.save(elasticAccount);

        return new ResponseEntity<>(accountDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount (@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        accountService.deleteAccount(id, userName);

        elasticAccountService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AccountDto>> searchAccountsByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserName = authentication.getName();

        UserDto authenticatedUser = userService.findByUserName(authenticatedUserName);

        if (authenticatedUser == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<ElasticAccount> accounts = elasticAccountService.findByUserId(authenticatedUser.getId());

        if (accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<AccountDto> accountDtoList = accounts.stream()
                .map(ElasticAccountMapper::mapToAccountDto)
                .toList();

        return new ResponseEntity<>(accountDtoList, HttpStatus.OK);
    }
}