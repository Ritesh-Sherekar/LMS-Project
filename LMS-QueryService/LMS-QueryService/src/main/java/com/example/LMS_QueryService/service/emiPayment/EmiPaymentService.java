package com.example.LMS_QueryService.service.emiPayment;

import com.example.LMS_QueryService.entity.EmiPayment;
import com.example.LMS_QueryService.exception.EmiPaymentNotFoundException;
import com.example.LMS_QueryService.repository.EmiPaymentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmiPaymentService {
    @Autowired
    private EmiPaymentRepo emiPaymentRepo;

    // Get All EMIPayment
    public List<EmiPayment> allEmiPayment(){
        log.info("Requesting For All Emi Payment!");
        List<EmiPayment> allEmiPayment = emiPaymentRepo.findAll();
        log.info("Response For All EMI Payment Is {}", allEmiPayment);
        return allEmiPayment;
    }

    // Get EmiPayment By ID
    public EmiPayment getEmiPaymentByID(int id){
        log.info("Requesting For EMI Payment By ID {}", id);
        EmiPayment emiPayment = emiPaymentRepo.findById(id).orElseThrow(()->{
            log.error("EMI Payment For Not Found!");
            return new EmiPaymentNotFoundException("EMI Payment Not Present!");
        });

        log.info("Response of EMIPayment For ID {} is {}", id, emiPayment);
        return emiPayment;
    }

    // Get EmiPayment By Loan ID
    public List<EmiPayment> getEmiPaymentByLoanID(int loanId){
        log.info("Requesting For EMI Payment By Loan ID {}", loanId);
        List<EmiPayment> byLoanID = emiPaymentRepo.findByLoanID(loanId);
        if (byLoanID == null){
            log.warn("EMI Payment For Not Found For Loan ID {}", loanId);
            return null;
        }

        log.info("Response of EMIPayment For Loan ID {} is {}", loanId, byLoanID);
        return byLoanID;
    }

    // Get Last EMI Payment
    public EmiPayment getLastEmiPayment(){
        log.info("Requesting for Last EMI Payment!");
        EmiPayment lastEmiPayment = emiPaymentRepo.findLastEmiPayment();

        log.info("Response of Last EMIPayment is {}", lastEmiPayment);
        return lastEmiPayment;
    }

    // Get Last EMI Payment For Specific Laon ID
    public EmiPayment getLastEmiPaymentByLoanID(int loanID){
        log.info("Requesting for Last EMI Payment By Loan ID {}", loanID);
        EmiPayment lastEmiPaymentByLoanId = emiPaymentRepo.findLastEmiPaymentByLoanId(loanID);

        log.info("Response of Last EMIPayment is {} for Loan ID {}", lastEmiPaymentByLoanId, loanID);
        return lastEmiPaymentByLoanId;
    }
}
