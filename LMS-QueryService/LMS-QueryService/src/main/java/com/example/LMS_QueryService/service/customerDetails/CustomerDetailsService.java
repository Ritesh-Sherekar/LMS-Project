package com.example.LMS_QueryService.service.customerDetails;

import com.example.LMS_QueryService.entity.CustomerDetails;
import com.example.LMS_QueryService.exception.CustomerDetailsNotFound;
import com.example.LMS_QueryService.exception.CustomerIDNotFoundException;
import com.example.LMS_QueryService.exception.CustomerTableIDNotPresentException;
import com.example.LMS_QueryService.repository.CustomerDetailsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerDetailsService {
    @Autowired
    private CustomerDetailsRepo customerDetailsRepo;

    // Get CustomerDetails By ID (Primary Key ID)
    public CustomerDetails getCustomerDetailsByID(int id){
        log.info("Request For CustomerDetails of ID {}", id);
        CustomerDetails customerDetails = customerDetailsRepo.findById(id).orElseThrow(() -> {
            log.error("CustomerDetails with ID {} not found!", id);
            return new CustomerDetailsNotFound("CustomerDetails Not Present!");
        });
        log.info("Response For ID {} Is {}", id, customerDetails );
        return customerDetails;
    }

    // get CustomerDetails By Customer ID
    public CustomerDetails getCustomerDetailsByCusID(String customerId){
        log.info("Request For CustomerDetails of Cus ID {}", customerId);
        CustomerDetails customerDetails = customerDetailsRepo.findByCustomerId(customerId);
        log.info("Response For Cus ID {} Is {}", customerId, customerDetails );

        if (customerDetails == null){
            log.error("CustomerDetails with Cus ID {} not found!", customerId);
            throw new CustomerIDNotFoundException("Customer ID Not Present!");
        }
        return customerDetails;
    }

    // get CustomerDetails By Customer table ID
    public CustomerDetails getCustomerDetailsByCustomerTableID(int id){
        log.info("Request For CustomerDetails of Customer Table ID {}", id);
        CustomerDetails byCustomerId = customerDetailsRepo.findByCustomer_Id(id);
        log.info("Response For Customer Table ID {} Is {}", id, byCustomerId);

        if (byCustomerId == null){
            log.error("CustomerDetails with Customer Table ID {} not found!", id);
            throw new CustomerTableIDNotPresentException("Customer Table ID Not Present!");
        }
        return byCustomerId;
    }



}
