package com.example.LMS_QueryService.exception;

public class UserNameNotFoundException extends RuntimeException{
    public UserNameNotFoundException(String message){
        super(message);
    }
}
