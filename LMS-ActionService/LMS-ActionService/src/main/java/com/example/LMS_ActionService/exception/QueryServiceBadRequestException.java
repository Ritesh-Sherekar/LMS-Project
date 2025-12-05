package com.example.LMS_ActionService.exception;

public class QueryServiceBadRequestException extends RuntimeException{
    public QueryServiceBadRequestException(String message){
        super(message);
    }
}
