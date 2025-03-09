package com.springboot.Banking_Application.service.impl;

import com.springboot.Banking_Application.dto.UserDto;
import com.springboot.Banking_Application.entity.User;
import com.springboot.Banking_Application.exception.UserNotFoundException;
import com.springboot.Banking_Application.mapper.UserMapper;
import com.springboot.Banking_Application.repository.UserRepository;
import com.springboot.Banking_Application.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(UserMapper::mapToUserDto).toList();
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User saveduser = userRepository.save(user);
        return UserMapper.mapToUserDto(saveduser);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        userRepository.deleteById(id);
    }
}
