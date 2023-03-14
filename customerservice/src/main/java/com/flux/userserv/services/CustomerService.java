package com.flux.userserv.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flux.userserv.entities.Customer;
@Service
public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomer(String id);
    void deleteCustomer(String id);
    Customer updateCustomer(Customer customer);
}
