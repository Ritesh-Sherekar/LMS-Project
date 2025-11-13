package com.example.LMS_QueryService.exception;

public class CustomerDetailsNotFound extends RuntimeException{
    public CustomerDetailsNotFound(String message){
        super(message);
    }
}
