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
        return new User(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getPassword(),
                userDto.getRole(),
                accountsList
        );
    }

    public static UserDto mapToUserDto (User user) {
        List<AccountDto> accountDtoList = user.getAccounts().stream()
                .map(AccountMapper::mapToAccountDto).toList();
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getRole(),
                accountDtoList
        );
    }
}