package com.flux.userserv.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flux.userserv.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
        String message=ex.getMessage();
        ApiResponse response=ApiResponse.builder().message(message).success(true).build();
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ApiResponse> handlerCustomerAlreadyExistsException(CustomerAlreadyExistsException ex){
        String message=ex.getMessage();
        ApiResponse response=ApiResponse.builder().message(message).success(true).build();
        return new ResponseEntity<ApiResponse>(response, HttpStatus.CONFLICT);
    }
}
