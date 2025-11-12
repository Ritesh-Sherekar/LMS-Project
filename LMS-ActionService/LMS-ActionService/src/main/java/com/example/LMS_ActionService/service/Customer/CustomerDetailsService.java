package com.example.LMS_ActionService.service.Customer;

import com.example.LMS_ActionService.entity.Customer;
import com.example.LMS_ActionService.entity.CustomerAddress;
import com.example.LMS_ActionService.entity.CustomerDetails;
import com.example.LMS_ActionService.entity.CustomerKyc;
import com.example.LMS_ActionService.repository.CustomerDetailsRepo;
import com.example.LMS_ActionService.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService {
    @Autowired
    private CustomerDetailsRepo customerDetailsRepo;

    @Autowired
    private CustomerRepo customerRepo;

    // Add All Details
    public CustomerDetails addAllInfo(CustomerDetails customerDetails){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Customer byUsername = customerRepo.findByUsername(userName);

        CustomerDetails cd = new CustomerDetails();
        cd.setCustomerId(customerDetails.getCustomerId());
        cd.setFirstName(customerDetails.getFirstName());
        cd.setLastName(customerDetails.getLastName());
        cd.setDateOfBirth(customerDetails.getDateOfBirth());
        cd.setGender(customerDetails.getGender());
        cd.setEmail(customerDetails.getEmail());
        cd.setPhoneNumber(customerDetails.getPhoneNumber());
        cd.setStatus(customerDetails.getStatus());

        CustomerAddress ca = new CustomerAddress();
        ca.setAddressLine1(customerDetails.getAddress().getAddressLine1());
        ca.setAddressLine2(customerDetails.getAddress().getAddressLine2());
        ca.setCity(customerDetails.getAddress().getCity());
        ca.setState(customerDetails.getAddress().getState());
        ca.setPincode(customerDetails.getAddress().getPincode());
        ca.setCountry(customerDetails.getAddress().getCountry());

        cd.setAddress(ca);

        CustomerKyc ck = new CustomerKyc();
        ck.setAadhaarNumber(customerDetails.getKycDetails().getAadhaarNumber());
        ck.setPanNumber(customerDetails.getKycDetails().getPanNumber());
        ck.setKycStatus(customerDetails.getKycDetails().getKycStatus());

        cd.setKycDetails(ck);
        cd.setCustomer(byUsername);

        CustomerDetails save = customerDetailsRepo.save(cd);
        return save;
    }
}
