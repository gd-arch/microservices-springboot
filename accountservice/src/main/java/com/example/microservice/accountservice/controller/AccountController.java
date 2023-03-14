package com.example.microservice.accountservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice.accountservice.dto.AccountInfo;
import com.example.microservice.accountservice.entities.Account;
import com.example.microservice.accountservice.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    static Logger log = LoggerFactory.getLogger(AccountController.class.getName());

    //create account
    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
       return new ResponseEntity<Account>(accountService.saveAccount(account), HttpStatus.CREATED); 
    }
    //get account by id
    @GetMapping("/{accountNo}")
    public ResponseEntity<Account> getAccount(@Valid @PathVariable String accountNo) {
        Account dbAccount=accountService.getAccountByAccountNo(accountNo);
        return ResponseEntity.ok(dbAccount);
    }
    //get all accounts
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> list=accountService.getAllAccounts();
        return ResponseEntity.ok(list);
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteByCustomerId(@Valid @PathVariable String customerId ){
    	accountService.deleteByCustomerId(customerId);
		return ResponseEntity.ok("Account Deleted successfully");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@Valid @RequestBody AccountInfo accountInfo){
         log.info(accountInfo.toString());
         if(accountInfo!=null)
         {
         boolean result=accountService.withdrawMoneyFromAccount(accountInfo);
         if(result){
           return new ResponseEntity<>("Amount "+accountInfo.getAmount()+" withdrawn from "+accountInfo.getAccountNo(), HttpStatus.OK);
         }
         }

          return new ResponseEntity<>(accountInfo, HttpStatus.BAD_REQUEST); 
        
    }
    @PostMapping("/deposit")
    public ResponseEntity<?> deposit( @Valid @RequestBody AccountInfo accountInfo){
        boolean result=accountService.depositMoneyToAccount(accountInfo);
        if(result){
            return new ResponseEntity<>("Amount "+accountInfo.getAmount()+" deposited to "+accountInfo.getAccountNo(), HttpStatus.OK);
          }else{
              return new ResponseEntity<>("Request Failed.Try again later", HttpStatus.BAD_REQUEST); 
          }
    }


}
