package com.example.LMS_ActionService.service.Loan;

import com.example.LMS_ActionService.entity.Loan;
import com.example.LMS_ActionService.repository.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    @Autowired
    private LoanRepo loanRepo;

    // Apply For Loan
    public Loan applyLoan(Loan loan){
        Loan save = loanRepo.save(loan);
        return save;
    }
}
