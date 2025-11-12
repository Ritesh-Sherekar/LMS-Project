package com.example.LMS_ActionService.repository;

import com.example.LMS_ActionService.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepo extends JpaRepository<Loan, Integer> {
}
