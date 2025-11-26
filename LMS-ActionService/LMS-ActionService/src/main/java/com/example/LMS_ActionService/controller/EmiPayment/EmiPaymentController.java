package com.example.LMS_ActionService.controller.EmiPayment;

import com.example.LMS_ActionService.dto.EmiPaymentDTO;
import com.example.LMS_ActionService.entity.EMI;
import com.example.LMS_ActionService.response.Response;
import com.example.LMS_ActionService.service.EmiPayment.EmiPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/emiPayment")
public class EmiPaymentController {
    @Autowired
    private EmiPaymentService emiPaymentService;

    // Get Emi By Loan ID
    @PostMapping("/makeEmiPayment")
    public ResponseEntity<Response<EMI>> getEmiByLoanId(@RequestBody EmiPaymentDTO emiPaymentDTO){
        Response<EMI> emiResponse = emiPaymentService.makePayment(emiPaymentDTO);
         //Response<EMI> response = new Response<>("Get EMI By Loan ID", LocalDateTime.now(), emiResponse, HttpStatus.OK.value());
        return new ResponseEntity<>(emiResponse, HttpStatus.OK);

    }
}
