package com.springboot.Banking_Application.elastic;

import com.springboot.Banking_Application.dto.AccountDto;

public class ElasticAccountMapper {

    public static ElasticAccount mapToElasticAccount(AccountDto accountDto, Long userId) {
        return ElasticAccount.builder()
                .id(accountDto.getId())
                .bankName(accountDto.getBankName())
                .accountHolderName(accountDto.getAccountHolderName())
                .balance(accountDto.getBalance())
                .userId(userId)
                .build();
    }

    public static AccountDto mapToAccountDto(ElasticAccount elasticAccount) {
        return AccountDto.builder()
                .id(elasticAccount.getId())
                .bankName(elasticAccount.getBankName())
                .accountHolderName(elasticAccount.getAccountHolderName())
                .balance(elasticAccount.getBalance())
                .build();
    }
}