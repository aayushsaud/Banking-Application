package com.springboot.Banking_Application.exception;

public class AccountNotFoundException extends RuntimeException  {

     public AccountNotFoundException (String message) {
         super(message);
     }
}