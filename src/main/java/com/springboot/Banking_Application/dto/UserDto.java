package com.springboot.Banking_Application.dto;

import com.springboot.Banking_Application.entity.Role;
import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private Long id;
    private String userName;
    private String password;
    private Role role;
    private List<AccountDto> accounts = new ArrayList<>();

    public UserDto() {
    }

    public UserDto(Long id, String userName, String password, Role role, List<AccountDto> accounts) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<AccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDto> accounts) {
        this.accounts = accounts;
    }
}