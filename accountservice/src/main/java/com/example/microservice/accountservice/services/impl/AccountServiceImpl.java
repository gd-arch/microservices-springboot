package com.example.microservice.accountservice.services.impl;


import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.microservice.accountservice.entities.Account;
import com.example.microservice.accountservice.entities.Customer;
import com.example.microservice.accountservice.controller.AccountController;
import com.example.microservice.accountservice.dto.AccountInfo;
import com.example.microservice.accountservice.exceptions.ResourceNotFoundException;
import com.example.microservice.accountservice.repositories.AccountRepository;
import com.example.microservice.accountservice.services.AccountService;



@Service

public class AccountServiceImpl implements AccountService{
    private static Logger log = LoggerFactory.getLogger(AccountController.class.getName());

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private WebClient.Builder webclientBuilder;
    @Autowired
    private RestTemplate restTemplate;
    

    @Override
    public Account saveAccount(Account account) {
        String randomAccountNo=UUID.randomUUID().toString();
        account.setAccountNo(randomAccountNo);
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
        
    }

    @Override
    public Account getAccount(long id) {
        return accountRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Account with id "+id+" not found "));
    }
    



    @Override
    public Account getAccountByAccountNo(String accountNo) {
    	
        Account accountFromDb=accountRepository.findByAccountNo(accountNo).orElseThrow(()->new ResourceNotFoundException("Account with number "+accountNo+" not found "));
        return accountFromDb;
    }

    @Transactional
    @Override
    public void deleteByCustomerId(String customerId) {
        accountRepository.deleteByCustomerId(customerId).orElseThrow(()->new ResourceNotFoundException("Account with customerId "+customerId+" not found "));
        
    }

    @Override
    public boolean depositMoneyToAccount(AccountInfo accountInfo) {
        Account accountFromDb=accountRepository.findByAccountNo(accountInfo.getAccountNo()).orElseThrow(()->new ResourceNotFoundException("Account with number "+accountInfo.getAccountNo()+" not found "));
        
        if(verifyCustomerDetailsUsingWebClient(accountFromDb.getCustomerId(),accountInfo.getAccountHolderName())){
            accountFromDb.setBalance(accountFromDb.getBalance()+accountInfo.getAmount());
            accountRepository.save(accountFromDb);
        return true;
        }
        return false;
    }

    @Override
    public boolean withdrawMoneyFromAccount(AccountInfo accountInfo) {
        Account accountFromDb=accountRepository.findByAccountNo(accountInfo.getAccountNo()).orElseThrow(()->new ResourceNotFoundException("Account with number "+accountInfo.getAccountNo()+" not found "));
        log.info(accountFromDb.toString());
        //check 
        if(verifyCustomerDetails(accountFromDb.getCustomerId(),accountInfo.getAccountHolderName())){
        accountFromDb.setBalance(accountFromDb.getBalance()-accountInfo.getAmount());
        accountRepository.save(accountFromDb);
        return true;
        }
        return false; 
    }

    private boolean verifyCustomerDetails(String customerId,String customerName) {
    	Customer customer=restTemplate.getForObject("http://CUSTOMER-SERVICE/customers/"+customerId, Customer.class);
        log.info(customer.toString());
        if(customer==null||!customer.getCustomerName().equals(customerName))return false;
        return true;
    }
    private boolean verifyCustomerDetailsUsingWebClient(String customerId,String customerName) {
    	Customer customer=webclientBuilder.build().get()
    			.uri("http://CUSTOMER-SERVICE/customers/"+customerId)
    			.retrieve().bodyToMono(Customer.class)
    			.block();
        log.info(customer.toString());
        if(customer==null||!customer.getCustomerName().equals(customerName))return false;
        return true;
    }
    
}
