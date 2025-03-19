package com.springboot.Banking_Application.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticAccountRepository extends ElasticsearchRepository<ElasticAccount, Long> {
    List<ElasticAccount> findByUserId(Long userId);

    List<ElasticAccount> findByBankName(String bankName);
}