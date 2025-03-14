package com.springboot.Banking_Application.mapper;

import com.springboot.Banking_Application.dto.AccountDto;
import com.springboot.Banking_Application.entity.Account;

public class AccountMapper {

    public static Account mapToAccount (AccountDto accountDto) {
        return Account.builder()
                .id(accountDto.getId())
                .bankName(accountDto.getBankName())
                .balance(accountDto.getBalance())
                .build();
    }

    public static AccountDto mapToAccountDto (Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .bankName(account.getBankName())
                .balance(account.getBalance())
                .build();
    }
}