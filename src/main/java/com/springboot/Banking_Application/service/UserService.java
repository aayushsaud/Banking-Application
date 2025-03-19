package com.springboot.Banking_Application.service;

import com.springboot.Banking_Application.dto.UserDto;
import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto createAdmin(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    UserDto findByUserName(String userName);

    List<UserDto> getAllUsers();

    void deleteByUserName(String userName);
}