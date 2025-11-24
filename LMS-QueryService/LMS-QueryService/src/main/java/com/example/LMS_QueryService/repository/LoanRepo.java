package com.example.LMS_QueryService.repository;

import com.example.LMS_QueryService.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepo extends JpaRepository<Loan, Integer> {
    Optional<Loan> findByLoanStatus(String status);
}
