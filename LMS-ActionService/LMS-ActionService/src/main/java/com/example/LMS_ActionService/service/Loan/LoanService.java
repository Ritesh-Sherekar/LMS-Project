package com.example.LMS_ActionService.service.Loan;

import com.example.LMS_ActionService.dto.LoanDTO;
import com.example.LMS_ActionService.entity.Customer;
import com.example.LMS_ActionService.entity.CustomerDetails;
import com.example.LMS_ActionService.entity.Loan;
import com.example.LMS_ActionService.entity.LoanType;
import com.example.LMS_ActionService.enums.Status;
import com.example.LMS_ActionService.repository.CustomerDetailsRepo;
import com.example.LMS_ActionService.repository.CustomerRepo;
import com.example.LMS_ActionService.repository.LoanRepo;
import com.example.LMS_ActionService.repository.LoanTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanService {
    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CustomerDetailsRepo customerDetailsRepo;

    @Autowired
    private LoanTypeRepo loanTypeRepo;

    // Apply For Loan
    public Loan applyLoan(LoanDTO loanDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Customer byUsername = customerRepo.findByUsername(userName);

        CustomerDetails byCusId = customerDetailsRepo.findByCustomer_Id(byUsername.getId());

        LoanType byLoanType = loanTypeRepo.findByLoanType(loanDTO.getLoanType());

        Loan loan = new Loan();
        loan.setLoanType(loanDTO.getLoanType());
        loan.setLoanAmount(loanDTO.getLoanAmount());
        loan.setTenureMonths(loanDTO.getTenureMonths());
        loan.setApplicationDate(LocalDate.now());
        loan.setLoanStatus(Status.PENDING.toString());
        loan.setEmploymentType(loanDTO.getEmploymentType());
        loan.setMonthlyIncome(loanDTO.getMonthlyIncome());
        loan.setInterestRate(byLoanType.getInterestRate());

        loan.setCustomerDetails(byCusId);

        return loanRepo.save(loan);
    }

    // Cancel the Loan Application


}
