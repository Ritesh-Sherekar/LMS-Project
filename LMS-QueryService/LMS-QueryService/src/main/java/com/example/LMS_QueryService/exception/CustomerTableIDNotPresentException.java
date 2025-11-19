package com.example.LMS_QueryService.exception;

public class CustomerTableIDNotPresentException extends RuntimeException {
    public CustomerTableIDNotPresentException(String message){
        super(message);
    }
}
