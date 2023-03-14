package com.flux.userserv.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.flux.userserv.entities.Customer;
import com.flux.userserv.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    //create customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
       return new ResponseEntity<Customer>(customerService.saveCustomer(customer), HttpStatus.CREATED); 
    }
    //get customer by id
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String customerId) {
        Customer dbCustomer=customerService.getCustomer(customerId);
        return ResponseEntity.ok(dbCustomer);
    }
    //get all customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> list=customerService.getAllCustomers();
        return ResponseEntity.ok(list);
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@Valid @PathVariable String customerId){
        Customer customer=customerService.getCustomer(customerId);
        if(customer==null)return ResponseEntity.notFound().build();
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Successfully deleted");

    }


}
