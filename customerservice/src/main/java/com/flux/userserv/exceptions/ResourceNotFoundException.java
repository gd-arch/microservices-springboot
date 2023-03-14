package com.flux.userserv.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){
        super("Resource not found exception");
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
