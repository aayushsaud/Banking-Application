package com.springboot.Banking_Application.service.impl;

import com.springboot.Banking_Application.entity.User;
import com.springboot.Banking_Application.exception.UserNotFoundException;
import com.springboot.Banking_Application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UserNotFoundException("User not found with username: " + userName);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}