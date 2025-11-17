package com.example.LMS_ActionService.controller.Loan;

import com.example.LMS_ActionService.dto.LoanDTO;
import com.example.LMS_ActionService.entity.Loan;
import com.example.LMS_ActionService.service.Loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;

    // Apply For Loan
    @PostMapping("/applyLoan")
    public ResponseEntity<Loan> applyLoan(@RequestBody LoanDTO loanDTO){
        Loan applyLoan = loanService.applyLoan(loanDTO);
        return ResponseEntity.ok(applyLoan);
    }

    // Cancel Loan
    @PutMapping("/cancelApplication")
    public ResponseEntity<String> cancelLoan(@RequestParam int id){
        String string = loanService.cancelLoanApplicationByID(id);
        return ResponseEntity.ok(string);
    }
}
