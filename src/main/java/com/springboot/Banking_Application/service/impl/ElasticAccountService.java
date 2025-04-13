package com.springboot.Banking_Application.service.impl;

import com.springboot.Banking_Application.elastic.ElasticAccount;
import com.springboot.Banking_Application.elastic.ElasticAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElasticAccountService {
    private final ElasticAccountRepository elasticAccountRepository;

    public ElasticAccountService(ElasticAccountRepository elasticAccountRepository) {
        this.elasticAccountRepository = elasticAccountRepository;
    }

    public void save(ElasticAccount elasticAccount) {
        elasticAccountRepository.save(elasticAccount);
    }

    public List<ElasticAccount> findByUserId(Long userId) {
        return elasticAccountRepository.findByUserId(userId);
    }

    public void deleteById(Long id) {
        elasticAccountRepository.deleteById(id);
    }
}
