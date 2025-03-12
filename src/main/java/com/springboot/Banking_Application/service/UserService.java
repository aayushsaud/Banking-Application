package com.springboot.Banking_Application.service;

import com.springboot.Banking_Application.dto.UserDto;
import com.springboot.Banking_Application.entity.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto createUser(UserDto userDto);

    UserDto findUserByUserName(String userName);

    void deleteUserById(Long id);
}