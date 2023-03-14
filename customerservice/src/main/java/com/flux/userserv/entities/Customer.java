package com.flux.userserv.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "micro_users")
public class Customer {
    @Column(name = "customer_id")
    @Id
    private String cId;
    @Column(name = "customer_name")
    @NotEmpty(message = "Customer name must not be empty")
    private String customerName;
    @Column(name = "customer_address")
    @NotEmpty(message = "Customer address must not be empty")
    private String address;

}