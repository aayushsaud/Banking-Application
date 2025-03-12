package com.springboot.Banking_Application.dto;

import com.springboot.Banking_Application.entity.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {

    private Long id;
    private String bankName;
    private String accountHolderName;
    private double balance;
}