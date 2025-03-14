package com.springboot.Banking_Application.mapper;

import com.springboot.Banking_Application.dto.AccountDto;
import com.springboot.Banking_Application.dto.UserDto;
import com.springboot.Banking_Application.entity.Account;
import com.springboot.Banking_Application.entity.User;

import java.util.List;

public class UserMapper {
    public static User mapToUser (UserDto userDto) {
        List<Account> accountsList = userDto.getAccounts().stream()
                .map(AccountMapper::mapToAccount).toList();
        return User.builder()
                .id((userDto.getId()))
                .userName(userDto.getUserName())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .accounts(accountsList)
                .build();
    }

    public static UserDto mapToUserDto (User user) {
        List<AccountDto> accountDtoList = user.getAccounts().stream()
                .map(AccountMapper::mapToAccountDto).toList();
        return UserDto.builder()
                .id((user.getId()))
                .userName(user.getUserName())
                .password(user.getPassword())
                .accounts(accountDtoList)
                .role(user.getRole())
                .build();
    }
}