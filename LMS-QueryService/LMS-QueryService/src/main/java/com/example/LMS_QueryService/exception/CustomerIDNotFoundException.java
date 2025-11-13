package com.example.LMS_QueryService.exception;

public class CustomerIDNotFoundException extends RuntimeException{
    public CustomerIDNotFoundException(String message){
        super(message);
    }
}
