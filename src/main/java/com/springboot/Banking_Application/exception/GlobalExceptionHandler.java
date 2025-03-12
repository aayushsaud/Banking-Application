package com.springboot.Banking_Application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> accountNotFoundExceptionHandler(AccountNotFoundException accountNotFoundException){
        ErrorResponse accountNotFound = new ErrorResponse(LocalDateTime.now(), accountNotFoundException.getMessage());
        return new ResponseEntity<>(accountNotFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundExceptionHandler(UserNotFoundException userNotFoundException){
        ErrorResponse userNotFound = new ErrorResponse(LocalDateTime.now(), userNotFoundException.getMessage());
        return new ResponseEntity<>(userNotFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<?> insufficientBalanceException(InsufficientBalanceException insufficientBalanceException){
        ErrorResponse insufficientBalance = new ErrorResponse(LocalDateTime.now(), insufficientBalanceException.getMessage());
        return new ResponseEntity<>(insufficientBalance, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> userAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException){
        ErrorResponse userAlreadyExists = new ErrorResponse(LocalDateTime.now(), userAlreadyExistsException.getMessage());
        return new ResponseEntity<>(userAlreadyExists, HttpStatus.FORBIDDEN);
    }
}