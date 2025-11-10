package com.example.LMS_SecurityService.controller;

import com.example.LMS_SecurityService.dto.CustomerDTO;
import com.example.LMS_SecurityService.dto.LoginDTO;
import com.example.LMS_SecurityService.dto.TokenDTO;
import com.example.LMS_SecurityService.entity.Customer;
import com.example.LMS_SecurityService.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // Register Customer
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerDTO customer){
        Customer registerCustomer = customerService.registerCustomer(customer);
        if (registerCustomer != null){
            return ResponseEntity.ok("Register Successfully");
        }
        return ResponseEntity.ok("Internal Server Error!");
    }

    // Login Customer
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> loginUser(@RequestBody LoginDTO loginDTO){
        TokenDTO tokenDTO = customerService.loginUser(loginDTO);
        return ResponseEntity.ok(tokenDTO);
    }

    // Get Active Token from Refresh Token
    @PostMapping("/getActiveToken")
    public ResponseEntity<TokenDTO> getActiveToken(@RequestBody String refreshToken){
        TokenDTO activeTokenFromRefreshToken = customerService.getActiveTokenFromRefreshToken(refreshToken);
        return ResponseEntity.ok(activeTokenFromRefreshToken);
    }

    // Get all user
    @GetMapping("/getUser")
    public ResponseEntity<List<Customer>> getAll(){
        List<Customer> customer = customerService.getCustomer();
        return ResponseEntity.ok(customer);
    }
}
