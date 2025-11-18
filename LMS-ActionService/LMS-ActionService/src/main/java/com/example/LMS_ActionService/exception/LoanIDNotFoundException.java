package com.example.LMS_ActionService.exception;

public class LoanIDNotFoundException extends RuntimeException{
    public LoanIDNotFoundException(String message){
        super(message);
    }
}
