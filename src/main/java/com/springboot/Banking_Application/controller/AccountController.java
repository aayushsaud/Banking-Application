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
import com.springboot.Banking_Application.service.AuthenticationService;
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

    private final AccountService accountService;
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final ElasticAccountService elasticAccountService;
    private String userName;

    public AccountController(AccountService accountService,
                             UserService userService, AuthenticationService authenticationService,
                             ElasticAccountService elasticAccountService) {
        this.accountService = accountService;
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.elasticAccountService = elasticAccountService;
    }

    @ModelAttribute
    public void setUserName() {
        this.userName = authenticationService.getAuthenticatedUserName();
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts () {
        List<AccountDto> accountDtoList = accountService.getAllAccounts(userName);

        if (accountDtoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(accountDtoList);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount (@RequestBody AccountDto accountDto) {
        AccountDto savedAccountDto = accountService.createAccount(accountDto, userName);

        Long userId = userService.findByUserName(userName).getId();
        ElasticAccount elasticAccount = ElasticAccountMapper.mapToElasticAccount(savedAccountDto, userId);
        elasticAccountService.save(elasticAccount);

        return ResponseEntity.ok(savedAccountDto);
    }

    @PutMapping("/deposit/{id}")
    public ResponseEntity<?> depositBalance (@PathVariable Long id, @RequestBody Map<String, Double> request) {
        AccountDto accountDto = accountService.depositBalance(id, userName, request.get("amount"));

        Long userId = userService.findByUserName(userName).getId();
        ElasticAccount elasticAccount = ElasticAccountMapper.mapToElasticAccount(accountDto, userId);
        elasticAccountService.save(elasticAccount);

        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/withdraw/{id}")
    public ResponseEntity<?> withdrawBalance (@PathVariable Long id, @RequestBody Map<String, Double> request) {
        AccountDto accountDto = accountService.withdrawBalance(id, userName, request.get("amount"));

        Long userId = userService.findByUserName(userName).getId();
        ElasticAccount elasticAccount = ElasticAccountMapper.mapToElasticAccount(accountDto, userId);
        elasticAccountService.save(elasticAccount);

        return ResponseEntity.ok(accountDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount (@PathVariable Long id) {
        accountService.deleteAccount(id, userName);
        elasticAccountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<AccountDto>> searchAccountsByUserId() {
        UserDto authenticatedUser = userService.findByUserName(userName);

        if (authenticatedUser == null) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }

        List<ElasticAccount> accounts = elasticAccountService.findByUserId(authenticatedUser.getId());

        if (accounts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<AccountDto> accountDtoList = accounts.stream()
                .map(ElasticAccountMapper::mapToAccountDto)
                .toList();

        return ResponseEntity.ok(accountDtoList);
    }
}