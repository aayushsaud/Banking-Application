package com.springboot.Banking_Application.elastic;

import com.springboot.Banking_Application.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticUserRepository extends ElasticsearchRepository<ElasticUser, Long> {
    Page<ElasticUser> findByUserName(String userName, Pageable pageable);
    Page<ElasticUser> findByRole(Role role, Pageable pageable);
}