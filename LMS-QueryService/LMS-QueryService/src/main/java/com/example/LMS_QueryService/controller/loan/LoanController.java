package com.example.LMS_QueryService.controller.loan;

import com.example.LMS_QueryService.dto.LoanDTOForResponse;
import com.example.LMS_QueryService.response.Response;
import com.example.LMS_QueryService.service.loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("queryLoan")
public class LoanController {
    @Autowired
    private LoanService loanService;

    // Get Loan By ID
    @GetMapping("/getLoanByID")
    public ResponseEntity<Response<LoanDTOForResponse>> getLoanByID(@RequestParam int id){
        LoanDTOForResponse loneByID = loanService.getLoneByID(id);
        Response<LoanDTOForResponse> response = new Response<>("Get Loan By ID", LocalDateTime.now(), loneByID, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get Loan By Loan Status
    @GetMapping("/getLoanByLoanStatus")
    public ResponseEntity<Response<LoanDTOForResponse>> getLoanByLoanStatus(@RequestParam String status){
        LoanDTOForResponse loanByLoanStatus = loanService.getLoanByLoanStatus(status);
        Response<LoanDTOForResponse> response = new Response<>("Get Loan By Loan Status", LocalDateTime.now(), loanByLoanStatus, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
