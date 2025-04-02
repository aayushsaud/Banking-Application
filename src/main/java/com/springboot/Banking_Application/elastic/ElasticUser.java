package com.springboot.Banking_Application.elastic;

import com.springboot.Banking_Application.entity.Role;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "users")
public class ElasticUser {
    private Long id;
    private String userName;
    private String password;
    private Role role;

    public ElasticUser() {
    }

    public ElasticUser(Long id, String userName, String password, Role role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
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
}
