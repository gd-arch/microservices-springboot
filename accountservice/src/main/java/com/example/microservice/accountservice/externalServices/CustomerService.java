package com.example.microservice.accountservice.externalServices;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.microservice.accountservice.config.LoadBalancerConfiguration;
import com.example.microservice.accountservice.entities.Customer;


	
@FeignClient(name = "CUSTOMER-SERVICE")
@LoadBalancerClient(name = "CUSTOMER-SERVICE",
configuration=LoadBalancerConfiguration.class)
public interface CustomerService {

    @GetMapping("/customers/{customerId}")
    Customer getCustomer(@PathVariable("customerId") String customerId);

    
    
}
