package com.example.LMS_ActionService.service;

import com.example.LMS_ActionService.entity.CustomerDetails;
import com.example.LMS_ActionService.repository.CustomerDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService {
    @Autowired
    private CustomerDetailsRepo customerDetailsRepo;

    // Add All Details
    public CustomerDetails addAllInfo(CustomerDetails customerDetails){
        CustomerDetails save = customerDetailsRepo.save(customerDetails);
        return save;
    }
}
