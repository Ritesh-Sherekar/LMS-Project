package com.example.LMS_QueryService.service.loan;

import com.example.LMS_QueryService.dto.LoanDTOForResponse;
import com.example.LMS_QueryService.entity.Loan;
import com.example.LMS_QueryService.exception.LoanIDNotFoundException;
import com.example.LMS_QueryService.repository.LoanRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoanService {
    @Autowired
    private LoanRepo loanRepo;

    // Get All By Loan ID
    public LoanDTOForResponse getLoneByID(int id){
        log.info("Request For Loan of ID {}", id);
        Loan loan = loanRepo.findById(id).orElseThrow(() -> {
            log.error("Loan with ID {} not found!", id);
            return new LoanIDNotFoundException("Loan ID Not Present");
        });
        log.info("Response For ID {} Is {} of Loan Object", id, loan);

        LoanDTOForResponse loanDTO = new LoanDTOForResponse();
        loanDTO.setId(loan.getId());
        loanDTO.setLoanType(loan.getLoanType());
        loanDTO.setLoanAmount(loan.getLoanAmount());
        loanDTO.setInterestRate(loan.getInterestRate());
        loanDTO.setTenureMonths(loan.getTenureMonths());
        loanDTO.setApplicationDate(loan.getApplicationDate());
        loanDTO.setApprovalDate(loan.getApprovalDate());
        loanDTO.setLoanStatus(loan.getLoanStatus());
        loanDTO.setEmiAmount(loan.getEmiAmount());
        loanDTO.setTotalPayableAmount(loan.getTotalPayableAmount());
        loanDTO.setRemainingBalance(loan.getRemainingBalance());
        loanDTO.setCustomerDetailsID(loan.getCustomerDetails().getCustomerId());

        log.info("Response For ID {} Is {} of LoanDTO Object", id, loanDTO);
        return loanDTO;
    }
}
