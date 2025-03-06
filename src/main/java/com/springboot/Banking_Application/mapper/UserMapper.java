package com.springboot.Banking_Application.mapper;

import com.springboot.Banking_Application.dto.UserDto;
import com.springboot.Banking_Application.entity.User;

public class UserMapper {
    public static User mapToUser (UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getPassword()
        );
    }

    public static UserDto mapToUserDto (User user) {
        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getPassword()
        );
    }
}