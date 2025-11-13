package com.example.LMS_QueryService.repository;

import com.example.LMS_QueryService.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepo extends JpaRepository<Loan, Integer> {
}
