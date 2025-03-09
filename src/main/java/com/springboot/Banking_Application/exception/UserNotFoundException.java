package com.springboot.Banking_Application.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException (String message) {
        super(message);
    }
}