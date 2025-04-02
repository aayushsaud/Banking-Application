package com.springboot.Banking_Application.controller;

import com.springboot.Banking_Application.dto.UserDto;
import com.springboot.Banking_Application.elastic.ElasticUser;
import com.springboot.Banking_Application.elastic.ElasticUserMapper;
import com.springboot.Banking_Application.service.UserService;
import com.springboot.Banking_Application.service.impl.ElasticUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;
    private final ElasticUserService elasticUserService;

    public AdminController(UserService userService, ElasticUserService elasticUserService) {
        this.userService = userService;
        this.elasticUserService = elasticUserService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser (@RequestBody UserDto userDto) {
        UserDto createdUserDto = userService.createAdmin(userDto);
        ElasticUser elasticUser = ElasticUserMapper.mapToElasticUser(createdUserDto);
        elasticUserService.save(elasticUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

    @GetMapping("/all-users")
    public ResponseEntity<Map<String, Object>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size) {
        return ResponseEntity.ok(userService.getAllUsers(page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchUsers(
            @RequestParam String column,
            @RequestParam String value,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size) {
        Map<String, Object> users = elasticUserService.searchByColumn(column, value, page, size);
        return ResponseEntity.ok(users);
    }
}