package com.example.LMS_QueryService.controller;

import com.example.LMS_QueryService.entity.CustomerDetails;
import com.example.LMS_QueryService.response.Response;
import com.example.LMS_QueryService.service.customerDetails.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("queryCustomerDetails")
public class CustomerDetailsController {
    @Autowired
    private CustomerDetailsService customerDetailsService;

    // Get CustomerDetails By ID
    @GetMapping("/getCustomerDetailsByID")
    public ResponseEntity<Response<CustomerDetails>> getCustomerDetailsByID(@RequestParam int id){
        CustomerDetails customerDetailsByID = customerDetailsService.getCustomerDetailsByID(id);
        Response<CustomerDetails> response = new Response<>("Get CustomerDetails By ID", LocalDateTime.now(), customerDetailsByID, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get CustomerDetails By Customer ID
    @GetMapping("/getCustomerDetailsByCusID")
    public ResponseEntity<Response<CustomerDetails>> getCustomerDetailsByCusID(@RequestParam String customerId){
        CustomerDetails customerDetailsByID = customerDetailsService.getCustomerDetailsByCusID(customerId);
        Response<CustomerDetails> response = new Response<>("Get CustomerDetails By ID", LocalDateTime.now(), customerDetailsByID, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
