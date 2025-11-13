package com.example.LMS_QueryService.service;

import com.example.LMS_QueryService.entity.Customer;
import com.example.LMS_QueryService.exception.UserNameNotFoundException;
import com.example.LMS_QueryService.exception.UserNotFoundException;
import com.example.LMS_QueryService.repository.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    // get Customer by id
    public Customer getCustomerByID(int id){
        log.info("Request For Customer of ID {}", id);
        Customer customer = customerRepo.findById(id).orElseThrow(() -> {
            log.error("Customer with ID {} not found!", id);
            return new UserNotFoundException("Customer Not Found!");
        });
        log.info("Response For ID {} Is {}", id, customer );
        return customer;
    }

    // get All Customer
    public List<Customer> getAllCustomer(){
        List<Customer> all = customerRepo.findAll();
        log.info("Getting All Customer {}", all);
        return all;
    }

    // get Customer By UserName
    public Customer getCustomerByUserName(String UserName){
        log.info("Request For Customer By username {}", UserName);
        Customer byUsername = customerRepo.findByUsername(UserName);
        log.info("Response For UserName {} Is {}", UserName, byUsername);

        if (byUsername == null){
            log.error("Customer with UserName {} not found!", UserName);
            throw new UserNameNotFoundException("Customer With this UserName Not Found");
        }

        return byUsername;
    }
}
