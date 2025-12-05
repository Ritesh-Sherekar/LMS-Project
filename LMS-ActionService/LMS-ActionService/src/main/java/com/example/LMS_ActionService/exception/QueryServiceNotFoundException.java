package com.example.LMS_ActionService.exception;

public class QueryServiceNotFoundException extends RuntimeException{
    public QueryServiceNotFoundException(String message){
        super(message);
    }
}
