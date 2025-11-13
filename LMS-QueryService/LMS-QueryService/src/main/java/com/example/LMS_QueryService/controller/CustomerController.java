package com.example.LMS_QueryService.controller;

import com.example.LMS_QueryService.entity.Customer;
import com.example.LMS_QueryService.response.Response;
import com.example.LMS_QueryService.service.customer.CustomerService;
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
@RequestMapping("/queryCustomer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // Get Customer By ID
    @GetMapping("/getCustomerByID")
    public ResponseEntity<Response<Customer>> getCustomerByID(@RequestParam int id){
        Customer customerByID = customerService.getCustomerByID(id);
        Response<Customer> response = new Response<>("Get Customer By ID", LocalDateTime.now(), customerByID, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get All Customer
    @GetMapping("/getAllCustomer")
    public ResponseEntity<Response<List<Customer>>> getAllCustomer(){
        List<Customer> allCustomer = customerService.getAllCustomer();
        Response<List<Customer>> response = new Response<>("Get All Customer", LocalDateTime.now(), allCustomer, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get Customer By UserName
    @GetMapping("/getCustomerByUserName")
    public ResponseEntity<Response<Customer>> getCustomerByUserName(@RequestParam String UserName){
        Customer customerByUserName = customerService.getCustomerByUserName(UserName);
        Response<Customer> response = new Response<>("Get Customer By Username", LocalDateTime.now(), customerByUserName, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
