package com.springboot.Banking_Application.controller;

import com.springboot.Banking_Application.dto.UserDto;
import com.springboot.Banking_Application.entity.User;
import com.springboot.Banking_Application.mapper.UserMapper;
import com.springboot.Banking_Application.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser (@RequestBody UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserDto userDto1 = userService.findByUserName(userName);

        User user = UserMapper.mapToUser(userDto1);
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());

        UserDto userDto2 = UserMapper.mapToUserDto(user);
        UserDto userDto3 = userService.updateUser(userDto2);
        return new ResponseEntity<>(userDto3, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userService.deleteByUserName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}