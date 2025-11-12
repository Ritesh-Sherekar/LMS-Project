package com.example.LMS_ActionService.controller.Loan;

import com.example.LMS_ActionService.entity.Loan;
import com.example.LMS_ActionService.service.Loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;

    // Apply For Loan
    @PostMapping("/applyLoan")
    public ResponseEntity<Loan> applyLoan(@RequestBody Loan loan){
        Loan applyLoan = loanService.applyLoan(loan);
        return ResponseEntity.ok(applyLoan);
    }
}
