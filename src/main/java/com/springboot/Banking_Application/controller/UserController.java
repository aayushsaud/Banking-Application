package com.springboot.Banking_Application.controller;

import com.springboot.Banking_Application.dto.UserDto;
import com.springboot.Banking_Application.entity.User;
import com.springboot.Banking_Application.mapper.UserMapper;
import com.springboot.Banking_Application.service.AuthenticationService;
import com.springboot.Banking_Application.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;
    private String userName;

    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @ModelAttribute
    public void setUserName() {
        this.userName = authenticationService.getAuthenticatedUserName();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser (@RequestBody UserDto userDto) {
        UserDto savedUser = userService.findByUserName(userName);

        User user = UserMapper.mapToUser(savedUser);
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());

        UserDto mappedUser = UserMapper.mapToUserDto(user);
        UserDto updatedUser = userService.updateUser(mappedUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser () {
        userService.deleteByUserName(userName);
        return ResponseEntity.noContent().build();
    }
}