package com.example.LMS_ActionService.controller.Customer;

import com.example.LMS_ActionService.entity.CustomerDetails;
import com.example.LMS_ActionService.service.Customer.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customerDetails")
public class CustomerDetailsController {
    @Autowired
    private CustomerDetailsService customerDetailsService;

    // Add All Info
    @PostMapping("/addAllInfo")
    public ResponseEntity<CustomerDetails> addAllInfo(@RequestBody CustomerDetails customerDetails){
        System.out.println("getting" + customerDetails);
        CustomerDetails saveInfo = customerDetailsService.addAllInfo(customerDetails);
        System.out.println("Response" + saveInfo);
        return ResponseEntity.ok(saveInfo);
    }
}
