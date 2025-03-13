package com.springboot.Banking_Application.repository;

import com.springboot.Banking_Application.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}