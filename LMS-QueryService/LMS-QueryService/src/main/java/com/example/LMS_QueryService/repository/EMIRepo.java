package com.example.LMS_QueryService.repository;

import com.example.LMS_QueryService.entity.EMI;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EMIRepo extends JpaRepository<EMI, Integer> {
    Optional<EMI> findByLoanID(int loanID);
}
