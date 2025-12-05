package com.example.LMS_ActionService.service.demo;

import com.example.LMS_ActionService.entity.EMI;
import com.example.LMS_ActionService.repository.ClientLoanIDRepo;
import com.example.LMS_ActionService.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoClass {
    @Autowired
    private ClientLoanIDRepo clientLoanIDRepo;

    // get loan by id
    public Response<EMI> getLoanByID(Integer loanID){
        return clientLoanIDRepo.getEmiByLoanID(loanID);
    }
}
