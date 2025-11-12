package com.example.LMS_SecurityService.exception;

import com.example.LMS_SecurityService.response.ErrorResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponses> userNotFound(UserNotFoundException ex){
        ErrorResponses error = new ErrorResponses(ex.getMessage(), "User Not Present!", LocalDateTime.now(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}

