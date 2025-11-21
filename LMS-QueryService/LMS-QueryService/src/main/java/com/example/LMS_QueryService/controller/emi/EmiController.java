package com.example.LMS_QueryService.controller.emi;

import com.example.LMS_QueryService.entity.CustomerDetails;
import com.example.LMS_QueryService.entity.EMI;
import com.example.LMS_QueryService.response.Response;
import com.example.LMS_QueryService.service.emi.EmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/queryEmi")
public class EmiController {
    @Autowired
    private EmiService emiService;

    // Get Emi Details By EMI ID
    @GetMapping("/getEmiByID")
    public ResponseEntity<Response<EMI>> getEmiByID(@RequestParam int id){
        EMI emiByID = emiService.getEmiByID(id);
        Response<EMI> response = new Response<>("Get EMI Details By ID", LocalDateTime.now(), emiByID, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get Emi Details By Loan ID
    @GetMapping("/getEmiByLoanID")
    public ResponseEntity<Response<EMI>> getEmiByLoanID(@RequestParam int LoanID){
        EMI emiByLoanId = emiService.getEmiByLoanId(LoanID);
        Response<EMI> response = new Response<>("Get EMI Details By Loan ID", LocalDateTime.now(), emiByLoanId, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
