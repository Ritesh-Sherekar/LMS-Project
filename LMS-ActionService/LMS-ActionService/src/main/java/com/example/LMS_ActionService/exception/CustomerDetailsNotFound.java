package com.example.LMS_ActionService.exception;

public class CustomerDetailsNotFound extends RuntimeException{
    public CustomerDetailsNotFound(String message){
        super(message);
    }
}
