package com.example.LMS_ActionService.exception;

public class CustomerIDNotFoundException extends RuntimeException{
    public CustomerIDNotFoundException(String message){
        super(message);
    }
}
