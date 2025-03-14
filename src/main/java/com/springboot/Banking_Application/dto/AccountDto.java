package com.springboot.Banking_Application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {

    private Long id;
    private String bankName;
    private double balance;
}