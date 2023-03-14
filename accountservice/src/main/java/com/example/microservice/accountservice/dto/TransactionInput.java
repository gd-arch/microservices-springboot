package com.example.microservice.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInput {
    private String sourceAccountNo;
    private String targetAccountNo;
    private String targetOwnerName;
    private double amount; 
}
