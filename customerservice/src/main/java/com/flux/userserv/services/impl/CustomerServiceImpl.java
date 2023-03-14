package com.flux.userserv.services.impl;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.flux.userserv.entities.Customer;
import com.flux.userserv.exceptions.ResourceNotFoundException;
import com.flux.userserv.repositories.CustomerRepository;
import com.flux.userserv.services.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Customer saveCustomer(Customer customer) {
        String randomCustomerId=UUID.randomUUID().toString();
        customer.setCId(randomCustomerId);
        while(customerRepository.findById(customer.getCId()).orElse(null)!=null)
        { 
            randomCustomerId=UUID.randomUUID().toString();
            customer.setCId(randomCustomerId);}
            Customer savedCustomer =customerRepository.save(customer);
        return savedCustomer;
        }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
        
    }

    @Override
    public Customer getCustomer(String id) {
        return customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer with id "+id+" not found "));
    }

    @Override
    public void deleteCustomer(String id) {
        //delete account with customer id
        restTemplate.delete("http://localhost:8087/accounts/"+id);
        customerRepository.deleteById(id);

    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerFromDb=customerRepository.findById(customer.getCId()).orElseThrow(()->new ResourceNotFoundException("Customer with id "+customer.getCId()+" not found "));
        customerFromDb.setAddress(customer.getAddress());
        customerFromDb.setCustomerName(customer.getCustomerName());
        return customerRepository.save(customerFromDb);
    }
    
}
