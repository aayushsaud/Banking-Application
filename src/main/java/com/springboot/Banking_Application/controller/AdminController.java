package com.springboot.Banking_Application.controller;

import com.springboot.Banking_Application.dto.UserDto;
import com.springboot.Banking_Application.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser (@RequestBody UserDto userDto) {
        UserDto createdUserDto = userService.createAdmin(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<?>> getAllUsers () {
        List<UserDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
}