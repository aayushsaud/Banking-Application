package com.springboot.Banking_Application.dto;

public class AccountDto {

    private Long id;
    private String bankName;
    private String accountHolderName;
    private double balance;

    public AccountDto() {
    }

    public AccountDto(Long id, String bankName, String accountHolderName, double balance) {
        this.id = id;
        this.bankName = bankName;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}