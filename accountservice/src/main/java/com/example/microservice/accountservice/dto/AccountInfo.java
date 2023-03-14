package com.example.microservice.accountservice.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AccountInfo {
    @NotBlank
    private String accountNo;
     @NotNull
     @Max(value=100000,message="Maximum amount that can be transferred is 1,00,000")
     @Min(value=1,message = "Maximum amount that can be transferred is 1")
    private Double amount;
     @NotBlank
    private String accountHolderName;
   
    //private int accountPin;
}

