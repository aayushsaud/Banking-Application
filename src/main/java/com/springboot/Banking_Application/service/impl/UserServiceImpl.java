package com.springboot.Banking_Application.service.impl;

import com.springboot.Banking_Application.dto.UserDto;
import com.springboot.Banking_Application.entity.Role;
import com.springboot.Banking_Application.entity.User;
import com.springboot.Banking_Application.exception.UserAlreadyExistsException;
import com.springboot.Banking_Application.exception.UserNotFoundException;
import com.springboot.Banking_Application.mapper.UserMapper;
import com.springboot.Banking_Application.repository.UserRepository;
import com.springboot.Banking_Application.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        if (userRepository.existsByUserName(userDto.getUserName())) {
            throw new UserAlreadyExistsException("Username '" + userDto.getUserName() + "' is already taken.");
        }
        User user = UserMapper.mapToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        User saveduser = userRepository.save(user);
        return UserMapper.mapToUserDto(saveduser);
    }

    @Override
    @Transactional
    public UserDto createAdmin(UserDto userDto) {
        if (userRepository.existsByUserName(userDto.getUserName())) {
            throw new UserAlreadyExistsException("Username '" + userDto.getUserName() + "' is already taken.");
        }
        User user = UserMapper.mapToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        User saveduser = userRepository.save(user);
        return UserMapper.mapToUserDto(saveduser);
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        user.setUserName(user.getUserName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saveduser = userRepository.save(user);
        return UserMapper.mapToUserDto(saveduser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        return UserMapper.mapToUserDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<User> userPage = userRepository.findAll(pageable);

        Page<UserDto> userDtoPage = userPage.map(UserMapper::mapToUserDto);

        Map<String, Object> response = new HashMap<>();
        response.put("users", userDtoPage.getContent());
        response.put("currentPage", userDtoPage.getNumber());
        response.put("totalPages", userDtoPage.getTotalPages());
        response.put("totalUsers", userDtoPage.getTotalElements());

        return response;
    }

    @Override
    @Transactional
    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }
}