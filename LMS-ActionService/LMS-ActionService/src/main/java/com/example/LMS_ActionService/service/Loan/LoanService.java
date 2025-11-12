package com.example.LMS_ActionService.service.Loan;

import com.example.LMS_ActionService.dto.LoanDTO;
import com.example.LMS_ActionService.entity.Customer;
import com.example.LMS_ActionService.entity.CustomerDetails;
import com.example.LMS_ActionService.entity.Loan;
import com.example.LMS_ActionService.repository.CustomerDetailsRepo;
import com.example.LMS_ActionService.repository.CustomerRepo;
import com.example.LMS_ActionService.repository.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CustomerDetailsRepo customerDetailsRepo;

    // Apply For Loan
    public Loan applyLoan(LoanDTO loanDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Customer byUsername = customerRepo.findByUsername(userName);
        System.out.println(byUsername);

        CustomerDetails byCusId = customerDetailsRepo.findByCustomer_Id(byUsername.getId());
        System.out.println(byCusId);

        Loan loan = new Loan();
        loan.setLoanType(loanDTO.getLoanType());
        loan.setLoanAmount(loanDTO.getLoanAmount());
        loan.setInterestRate(loanDTO.getInterestRate());
        loan.setTenureMonths(loanDTO.getTenureMonths());
        loan.setApplicationDate(loanDTO.getApplicationDate());
        loan.setApprovalDate(loanDTO.getApprovalDate());
        loan.setLoanStatus(loanDTO.getLoanStatus());
        loan.setEmiAmount(loanDTO.getEmiAmount());
        loan.setTotalPayableAmount(loanDTO.getTotalPayableAmount());
        loan.setRemainingBalance(loanDTO.getRemainingBalance());
        loan.setCustomerDetails(byCusId);

        return loanRepo.save(loan);
    }
}
