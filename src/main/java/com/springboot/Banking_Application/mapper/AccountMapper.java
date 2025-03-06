package com.springboot.Banking_Application.mapper;

import com.springboot.Banking_Application.dto.AccountDto;
import com.springboot.Banking_Application.entity.Account;

public class AccountMapper {

    public static Account mapToAccount (AccountDto accountDto) {
        return new Account(
                accountDto.getId(),
                accountDto.getBankName(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
    }

    public static AccountDto mapToAccountDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getBankName(),
                account.getAccountHolderName(),
                account.getBalance()
        );
    }
}