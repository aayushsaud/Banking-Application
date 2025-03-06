package com.springboot.Banking_Application.service;

import com.springboot.Banking_Application.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto createUser(UserDto userDto);

    void deleteUserById(Long id);
}