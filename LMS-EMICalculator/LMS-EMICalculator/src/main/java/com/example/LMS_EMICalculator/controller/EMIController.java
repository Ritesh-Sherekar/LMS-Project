package com.example.LMS_EMICalculator.controller;

import com.example.LMS_EMICalculator.dto.RequestDTO;
import com.example.LMS_EMICalculator.dto.ResponseDTO;
import com.example.LMS_EMICalculator.service.EMIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emiCalculator")
public class EMIController {
//    @Autowired
//    private EMIService emiService;
//
//    @GetMapping("/getEmi")
//    public ResponseEntity<ResponseDTO> calculateEMI(@RequestBody RequestDTO requestDTO){
//        ResponseDTO responseDTO = emiService.calculateEMI(requestDTO);
//        return ResponseEntity.ok(responseDTO);
//    }
}
