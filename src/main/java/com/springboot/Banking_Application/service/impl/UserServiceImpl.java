package com.springboot.Banking_Application.service.impl;

import com.springboot.Banking_Application.dto.UserDto;
import com.springboot.Banking_Application.entity.User;
import com.springboot.Banking_Application.exception.UserAlreadyExistsException;
import com.springboot.Banking_Application.mapper.UserMapper;
import com.springboot.Banking_Application.repository.UserRepository;
import com.springboot.Banking_Application.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }
}