package com.springboot.Banking_Application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> accountNotFoundExceptionHandler(AccountNotFoundException exception){
        ErrorResponse accountNotFound = new ErrorResponse(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(accountNotFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundExceptionHandler(UserNotFoundException exception){
        ErrorResponse userNotFound = new ErrorResponse(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(userNotFound, HttpStatus.NOT_FOUND);
    }
}