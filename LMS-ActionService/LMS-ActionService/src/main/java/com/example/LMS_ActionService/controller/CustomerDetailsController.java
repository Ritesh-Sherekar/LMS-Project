package com.example.LMS_ActionService.controller;

import com.example.LMS_ActionService.entity.CustomerDetails;
import com.example.LMS_ActionService.service.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customerDetails")
public class CustomerDetailsController {
    @Autowired
    private CustomerDetailsService customerDetailsService;

    // Add All Info
    @PostMapping("/addAllInfo")
    public ResponseEntity<CustomerDetails> addAllInfo(@RequestBody CustomerDetails customerDetails){
        CustomerDetails saveInfo = customerDetailsService.addAllInfo(customerDetails);
        return ResponseEntity.ok(saveInfo);
    }
}
