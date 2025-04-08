package com.springboot.Banking_Application.elastic;

import jakarta.persistence.*;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "accounts")
public class ElasticAccount {
    @Id
    private Long id;
    private String bankName;
    private String accountHolderName;
    private double balance;
    private Long userId;

    public ElasticAccount(){
    }

    public ElasticAccount(Long id, String bankName, String accountHolderName, double balance, Long userId) {
        this.id = id;
        this.bankName = bankName;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.userId = userId;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}