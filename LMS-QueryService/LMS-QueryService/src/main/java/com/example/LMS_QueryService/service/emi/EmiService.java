package com.example.LMS_QueryService.service.emi;

import com.example.LMS_QueryService.entity.EMI;
import com.example.LMS_QueryService.exception.EmiIDNotFoundException;
import com.example.LMS_QueryService.exception.LoanIDNotFoundException;
import com.example.LMS_QueryService.repository.EMIRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmiService {
    @Autowired
    private EMIRepo emiRepo;

    // Get Emi Base On EMI ID
    public EMI getEmiByID(int id){
        log.info("Requesting For emil Details by EMI ID {}", id);

        EMI emiIdNotPresent = emiRepo.findById(id).orElseThrow(() -> {
            log.error("Getting error because, EMI ID not Found");
            return new EmiIDNotFoundException("EMI ID Not Present");
        });

        log.info("Response For EMI ID {} is {}", id, emiIdNotPresent);
        return emiIdNotPresent;
    }

    // Get Emi By Loan ID
    public EMI getEmiByLoanId(int LoanID){
        log.info("Requesting For emi Details Base on Loan ID {}", LoanID);

        EMI loanIdNotPresent = emiRepo.findByLoanID(LoanID).orElseThrow(() -> {
            log.error("Getting Error Because of Loan ID not Found");
            return new LoanIDNotFoundException("Loan ID Not Present");
        });

        log.info("Response For Loan ID {} is {}", LoanID, loanIdNotPresent);
        return loanIdNotPresent;
    }
}
