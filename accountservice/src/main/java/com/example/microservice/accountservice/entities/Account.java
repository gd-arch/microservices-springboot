package com.example.microservice.accountservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "micro_account")
public class Account {
    @Column(name = "account_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "account_no",unique=true)
    private String accountNo;
    @Column(name = "customer_id")
    @NotEmpty(message = "Customer id must not be empty")
    private String customerId;
    @NotNull(message = "Account balance must not be null")
    @Min(value=5000,message="Minimum account balance must be greater than equal to 5000")
    @Column(name = "balance")
    private Double balance;
    // private transient List<Transaction> transactions;


}

