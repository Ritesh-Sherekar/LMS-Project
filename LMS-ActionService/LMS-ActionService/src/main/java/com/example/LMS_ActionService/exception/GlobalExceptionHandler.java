package com.example.LMS_ActionService.exception;

import com.example.LMS_ActionService.response.ErrorResponses;
import feign.FeignException;
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

    @ExceptionHandler(InvalidApplicationStatusException.class)
    public ResponseEntity<ErrorResponses> invalidStatus(InvalidApplicationStatusException ex){
        ErrorResponses error = new ErrorResponses(ex.getMessage(), "Invalid Application Status", LocalDateTime.now(), HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    // ---------------------- Loan -------------------------


    // ---------------------- Feign Exception -------------------------

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponses> globalFeignException(FeignException ex){
        ErrorResponses error = new ErrorResponses(ex.getMessage(), "Query Service Not Available", LocalDateTime.now(), HttpStatus.SERVICE_UNAVAILABLE);
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(QueryServiceNotFoundException.class)
    public ResponseEntity<ErrorResponses> queryServiceNotFound(QueryServiceNotFoundException ex){
        ErrorResponses error = new ErrorResponses(ex.getMessage(), "Query Service Not Found Proper Result", LocalDateTime.now(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QueryServiceBadRequestException.class)
    public ResponseEntity<ErrorResponses> queryServiceBadRequest(QueryServiceBadRequestException ex){
        ErrorResponses error = new ErrorResponses(ex.getMessage(), "Query Service Not Expect This Request", LocalDateTime.now(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(ServiceUnavailableException.class)
//    public ResponseEntity<ErrorResponses> serviceUnavailable(ServiceUnavailableException ex){
//        ErrorResponses error = new ErrorResponses(ex.getMessage(), "Query Service Unavailable Right Now, Try After Some Time!", LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR);
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }


    // ---------------------- Feign Exception -------------------------



}

