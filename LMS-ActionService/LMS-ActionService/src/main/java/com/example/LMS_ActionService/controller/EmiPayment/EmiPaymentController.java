package com.example.LMS_ActionService.controller.EmiPayment;

import com.example.LMS_ActionService.dto.EmiPaymentDTO;
import com.example.LMS_ActionService.entity.EmiPayment;
import com.example.LMS_ActionService.response.Response;
import com.example.LMS_ActionService.service.EmiPayment.EmiPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/emiPayment")
public class EmiPaymentController {
    @Autowired
    private EmiPaymentService emiPaymentService;

    // Get Emi By Loan ID
    @PostMapping("/makeEmiPayment")
    public ResponseEntity<Response<EmiPayment>> getEmiByLoanId(@RequestBody EmiPaymentDTO emiPaymentDTO){
        EmiPayment emiPayment = emiPaymentService.makePayment(emiPaymentDTO);
        Response<EmiPayment> response = new Response<>("Make EMI Payment", LocalDateTime.now(), emiPayment, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
