package com.springboot.Banking_Application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "account_holder_name", unique = true)
    private String accountHolderName;
    @Column(name = "balance")
    private double balance;
}