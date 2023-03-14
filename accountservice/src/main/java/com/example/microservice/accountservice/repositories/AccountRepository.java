package com.example.microservice.accountservice.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservice.accountservice.entities.Account;

public interface AccountRepository extends JpaRepository<Account,Long>{
    Optional<Account> findByAccountNo(String accountNo);
    Optional<Account> deleteByCustomerId(String customerId);

}