package com.example.LMS_ActionService.repository;

import com.example.LMS_ActionService.entity.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTypeRepo extends JpaRepository<LoanType, Integer> {
    LoanType findByLoanType(String loanType);
}
