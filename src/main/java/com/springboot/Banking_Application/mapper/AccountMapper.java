package com.springboot.Banking_Application.mapper;

import com.springboot.Banking_Application.dto.AccountDTO;
import com.springboot.Banking_Application.entity.Account;

public class AccountMapper {
    public static Account mapToAccount (AccountDTO accountDTO) {
        return new Account(
                accountDTO.getId(),
                accountDTO.getAccountHolderName(),
                accountDTO.getBalance()
        );
    }

    public static AccountDTO mapToAccountDTO(Account account) {
        return new AccountDTO(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
    }
}