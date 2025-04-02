package com.springboot.Banking_Application.elastic;

import com.springboot.Banking_Application.dto.UserDto;
import com.springboot.Banking_Application.entity.User;

public class ElasticUserMapper {
    public static ElasticUser mapToElasticUser(UserDto userDto) {
        return new ElasticUser(
                userDto.getId(),
                userDto.getUserName(),
                userDto.getPassword(),
                userDto.getRole()
        );
    }

    public static User mapToUserDto(ElasticUser elasticUser) {
        return new User(
                elasticUser.getId(),
                elasticUser.getUserName(),
                elasticUser.getPassword(),
                elasticUser.getRole()
        );
    }
}