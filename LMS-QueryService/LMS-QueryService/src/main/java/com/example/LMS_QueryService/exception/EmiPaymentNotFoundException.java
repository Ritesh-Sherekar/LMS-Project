package com.example.LMS_QueryService.exception;

public class EmiPaymentNotFoundException extends RuntimeException{
    public EmiPaymentNotFoundException(String message){
        super(message);
    }
}
