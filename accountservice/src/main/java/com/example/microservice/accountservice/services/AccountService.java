package com.example.microservice.accountservice.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.microservice.accountservice.entities.Account;
import com.example.microservice.accountservice.dto.AccountInfo;

@Service

public interface AccountService {
    Account saveAccount(Account account);
    List<Account> getAllAccounts();
    Account getAccount(long id);
    Account getAccountByAccountNo(String accountNo);
    void deleteByCustomerId(String customerId);
    boolean depositMoneyToAccount(AccountInfo accountInfo);
    boolean withdrawMoneyFromAccount(AccountInfo accountInfo);

}
