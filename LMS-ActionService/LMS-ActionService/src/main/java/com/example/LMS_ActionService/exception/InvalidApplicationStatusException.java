package com.example.LMS_ActionService.exception;

public class InvalidApplicationStatusException extends RuntimeException{
    public InvalidApplicationStatusException(String message){
        super(message);
    }
}
