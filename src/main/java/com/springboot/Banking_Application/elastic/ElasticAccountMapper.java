package com.springboot.Banking_Application.elastic;

import com.springboot.Banking_Application.dto.AccountDto;

public class ElasticAccountMapper {

    public static ElasticAccount mapToElasticAccount(AccountDto accountDto, Long userId) {
        return new ElasticAccount(
                accountDto.getId(),
                accountDto.getBankName(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance(),
                userId);
    }

    public static AccountDto mapToAccountDto(ElasticAccount elasticAccount) {
        return new AccountDto(
                elasticAccount.getId(),
                elasticAccount.getBankName(),
                elasticAccount.getAccountHolderName(),
                elasticAccount.getBalance());
    }
}