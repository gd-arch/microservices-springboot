package com.flux.userserv.repositories;
import com.flux.userserv.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String>{

}