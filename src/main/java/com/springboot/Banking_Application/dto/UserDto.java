package com.springboot.Banking_Application.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String userName;
    private String password;
    private List<AccountDto> accounts = new ArrayList<>();
}