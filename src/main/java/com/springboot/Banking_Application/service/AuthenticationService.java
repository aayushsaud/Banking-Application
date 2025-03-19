package com.springboot.Banking_Application.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public String getAuthenticatedUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication != null) ? authentication.getName() : null;
    }
}
