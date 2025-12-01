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
    public EmiPayment getEmiPaymentByLoanID(int loanId){
        log.info("Requesting For EMI Payment By Loan ID {}", loanId);
        EmiPayment emiPayment = emiPaymentRepo.findByLoanID(loanId);
        if (emiPayment == null){
            throw new EmiPaymentNotFoundException("EMI Payment Not Present!");
        }

        log.info("Response of EMIPayment For Loan ID {} is {}", loanId, emiPayment);
        return emiPayment;
    }

    // Get Last EMI Payment
    public EmiPayment getLastEmiPayment(){
        log.info("Requesting for Last EMI Payment!");
        EmiPayment lastEmiPayment = emiPaymentRepo.findLastEmiPayment();

        log.info("Response of Last EMIPayment is {}", lastEmiPayment);
        return lastEmiPayment;
    }
}
