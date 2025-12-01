package com.example.LMS_QueryService.controller.emiPayment;

import com.example.LMS_QueryService.entity.EMI;
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

@RestController
@RequestMapping("queryEmiPayment")
public class EmiPaymentController {
    @Autowired
    private EmiPaymentService emiPaymentService;

    // Get EMI Payment By ID
    @GetMapping("/getEmiPaymentByID")
    public ResponseEntity<Response<EmiPayment>> getEmiPaymentByID(@RequestParam int id){
        EmiPayment emiPaymentByID = emiPaymentService.getEmiPaymentByID(id);
        Response<EmiPayment> response = new Response<>("Get EMIPayment Details By ID", LocalDateTime.now(), emiPaymentByID, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get EMI Payment By Loan ID
    @GetMapping("/getEmiPaymentByLoanID")
    public ResponseEntity<Response<EmiPayment>> getEmiPaymentByLoanID(@RequestParam int loanID){
        EmiPayment emiPaymentByID = emiPaymentService.getEmiPaymentByLoanID(loanID);
        Response<EmiPayment> response = new Response<>("Get EMIPayment Details By Loan ID", LocalDateTime.now(), emiPaymentByID, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get Last EMIPayment
    @GetMapping("/getLastEmiPayment")
    public ResponseEntity<Response<EmiPayment>> getLastEmiPayment(){
        EmiPayment lastEmiPayment = emiPaymentService.getLastEmiPayment();
        Response<EmiPayment> response = new Response<>("Get Last EMIPayment Details", LocalDateTime.now(), lastEmiPayment, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
