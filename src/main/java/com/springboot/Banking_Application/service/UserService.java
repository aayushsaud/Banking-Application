package com.springboot.Banking_Application.service;

import com.springboot.Banking_Application.dto.UserDto;
import java.util.List;
import java.util.Map;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto createAdmin(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    UserDto findByUserName(String userName);

    Map<String, Object> getAllUsers(int page, int size);

    void deleteByUserName(String userName);
}