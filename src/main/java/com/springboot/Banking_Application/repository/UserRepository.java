package com.springboot.Banking_Application.repository;

import com.springboot.Banking_Application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}