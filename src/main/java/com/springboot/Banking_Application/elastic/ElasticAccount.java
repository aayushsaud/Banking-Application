package com.springboot.Banking_Application.elastic;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "accounts")
public class ElasticAccount {
    @Id
    private Long id;
    private String bankName;
    private String accountHolderName;
    private double balance;
    private Long userId;
}