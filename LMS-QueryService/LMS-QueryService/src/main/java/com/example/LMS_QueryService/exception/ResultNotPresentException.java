package com.example.LMS_QueryService.exception;

public class ResultNotPresentException extends RuntimeException{
    public ResultNotPresentException(String message){
        super(message);
    }
}
