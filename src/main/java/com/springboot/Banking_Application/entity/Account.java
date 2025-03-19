package com.springboot.Banking_Application.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    @Column(name = "balance")
    private double balance;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false)
    private User user;

    public Account() {
    }

    public Account(Long id, String bankName, String accountHolderName, double balance, User user) {
        this.id = id;
        this.bankName = bankName;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.user = user;
    }

    public Account(Long id, String bankName, String accountHolderName, double balance) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}