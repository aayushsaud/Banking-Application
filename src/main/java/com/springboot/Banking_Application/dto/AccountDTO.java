package com.springboot.Banking_Application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDTO {
    private Long id;
    private String accountHolderName;
    private double balance;
}
