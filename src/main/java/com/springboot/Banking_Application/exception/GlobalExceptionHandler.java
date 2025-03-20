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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(accountNotFound);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundExceptionHandler(UserNotFoundException userNotFoundException){
        ErrorResponse userNotFound = new ErrorResponse(LocalDateTime.now(), userNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<?> insufficientBalanceException(InsufficientBalanceException insufficientBalanceException){
        ErrorResponse insufficientBalance = new ErrorResponse(LocalDateTime.now(), insufficientBalanceException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(insufficientBalance);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> userAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException){
        ErrorResponse userAlreadyExists = new ErrorResponse(LocalDateTime.now(), userAlreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(userAlreadyExists);
    }
}