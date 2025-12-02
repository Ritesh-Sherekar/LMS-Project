package com.example.LMS_QueryService.controller.emiPayment;

import com.example.LMS_QueryService.entity.EmiPayment;
import com.example.LMS_QueryService.response.Response;
import com.example.LMS_QueryService.service.emiPayment.EmiPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("queryEmiPayment")
public class EmiPaymentController {
    @Autowired
    private EmiPaymentService emiPaymentService;

    // Get All Emi Payment
    @GetMapping("/getAllEmiPayment")
    public ResponseEntity<Response<List<EmiPayment>>> getAllEmiPayment(){
        List<EmiPayment> emiPayments = emiPaymentService.allEmiPayment();
        Response<List<EmiPayment>> response = new Response<>("Get All EMIPayment Details", LocalDateTime.now(), emiPayments, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get EMI Payment By ID
    @GetMapping("/getEmiPaymentByID")
    public ResponseEntity<Response<EmiPayment>> getEmiPaymentByID(@RequestParam int id){
        EmiPayment emiPaymentByID = emiPaymentService.getEmiPaymentByID(id);
        Response<EmiPayment> response = new Response<>("Get EMIPayment Details By ID", LocalDateTime.now(), emiPaymentByID, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get EMI Payment By Loan ID
    @GetMapping("/getEmiPaymentByLoanID")
    public ResponseEntity<Response<List<EmiPayment>>> getEmiPaymentByLoanID(@RequestParam int loanID){
        List<EmiPayment> emiPaymentByLoanID = emiPaymentService.getEmiPaymentByLoanID(loanID);
        Response<List<EmiPayment>> response = new Response<>("Get EMIPayment Details By Loan ID", LocalDateTime.now(), emiPaymentByLoanID, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get Last EMIPayment
    @GetMapping("/getLastEmiPayment")
    public ResponseEntity<Response<EmiPayment>> getLastEmiPayment(){
        EmiPayment lastEmiPayment = emiPaymentService.getLastEmiPayment();
        Response<EmiPayment> response = new Response<>("Get Last EMIPayment Details", LocalDateTime.now(), lastEmiPayment, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get Last EMIPayment By Loan ID
    @GetMapping("/getLastEmiPaymentByLoanID")
    public ResponseEntity<Response<EmiPayment>> getLastEmiPaymentByLoanID(@RequestParam int loanID){
        EmiPayment lastEmiPaymentByLoanID = emiPaymentService.getLastEmiPaymentByLoanID(loanID);
        Response<EmiPayment> response = new Response<>("Get Last EMIPayment Details By Loan ID", LocalDateTime.now(), lastEmiPaymentByLoanID, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
