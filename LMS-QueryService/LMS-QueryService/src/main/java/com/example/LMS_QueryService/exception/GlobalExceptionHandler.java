package com.example.LMS_QueryService.exception;

import com.example.LMS_QueryService.response.ErrorResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //------------------------- Customer --------------------
    // User Not Found
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponses> userNotFound(UserNotFoundException ex){
        ErrorResponses error = new ErrorResponses(ex.getMessage(), "User Not Present!", LocalDateTime.now(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // UserName Not Found
    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<ErrorResponses> userNameNotFound(UserNameNotFoundException ex){
        ErrorResponses error = new ErrorResponses(ex.getMessage(), "UserName Not Found!", LocalDateTime.now(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    //------------------------- Customer --------------------

    // ----------------------- CustomerDetails ------------------

    // CustomerDetails Not Found with ID
    @ExceptionHandler(CustomerDetailsNotFound.class)
    public ResponseEntity<ErrorResponses> customerDetailsNotFound(CustomerDetailsNotFound ex){
        ErrorResponses error = new ErrorResponses(ex.getMessage(), "CustomerDetails Not Found!", LocalDateTime.now(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Customer ID Not found
    @ExceptionHandler(CustomerIDNotFoundException.class)
    public ResponseEntity<ErrorResponses> customerIDNotFound(CustomerIDNotFoundException ex){
        ErrorResponses error = new ErrorResponses(ex.getMessage(), "Customer ID Not Found!", LocalDateTime.now(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // ---------------------- Loan -------------------------
    @ExceptionHandler(LoanIDNotFoundException.class)
    public ResponseEntity<ErrorResponses> loanIdNotFound(LoanIDNotFoundException ex){
        ErrorResponses error = new ErrorResponses(ex.getMessage(), "Loan ID Not Found!", LocalDateTime.now(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}

