package com.springboot.Banking_Application.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
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
}