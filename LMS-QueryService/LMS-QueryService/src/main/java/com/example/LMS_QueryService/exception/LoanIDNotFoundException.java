package com.example.LMS_QueryService.exception;

public class LoanIDNotFoundException extends RuntimeException{
    public LoanIDNotFoundException(String message){
        super(message);
    }
}
