package com.springboot.Banking_Application.service.impl;

import com.springboot.Banking_Application.elastic.ElasticUser;
import com.springboot.Banking_Application.elastic.ElasticUserRepository;
import com.springboot.Banking_Application.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class ElasticUserService {

    private final ElasticUserRepository elasticUserRepository;

    public ElasticUserService(ElasticUserRepository elasticUserRepository) {
        this.elasticUserRepository = elasticUserRepository;
    }

    public ElasticUser save(ElasticUser elasticUser) {
        return elasticUserRepository.save(elasticUser);
    }

    public Map<String, Object> searchByColumn(String column, String value, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ElasticUser> userPage;

        if (column == null || value == null) {
            return Collections.emptyMap();
        }

        switch (column) {
            case "userName":
                userPage = elasticUserRepository.findByUserName(value, pageable);
                break;
            case "role":
                try {
                    Role role = Role.valueOf(value.toUpperCase());
                    userPage = elasticUserRepository.findByRole(role, pageable);
                } catch (IllegalArgumentException e) {
                    return Collections.emptyMap();
                }
                break;
            default:
                return Collections.emptyMap();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("users", userPage.getContent());
        response.put("currentPage", userPage.getNumber());
        response.put("totalPages", userPage.getTotalPages());
        response.put("totalItems", userPage.getTotalElements());

        return response;
    }
}