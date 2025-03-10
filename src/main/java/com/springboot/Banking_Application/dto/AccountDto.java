package com.springboot.Banking_Application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long id;
    private String bankName;
    private String accountHolderName;
    private double balance;
}