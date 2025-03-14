package com.springboot.Banking_Application.controller;

import com.springboot.Banking_Application.dto.UserDto;
import com.springboot.Banking_Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser (@RequestBody UserDto userDto) {
        UserDto userDto1 = userService.createAdmin(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<?>> getAllUsers () {
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
