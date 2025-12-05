package com.example.LMS_ActionService.exception;

public class ServiceUnavailableException extends RuntimeException {
    public ServiceUnavailableException(String message){
        super(message);
    }
}
