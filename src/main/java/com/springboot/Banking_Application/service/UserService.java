package com.springboot.Banking_Application.service;

import com.springboot.Banking_Application.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    UserDto findByUserName(String userName);

    void deleteByUserName(String userName);
}