package com.example.LMS_EMICalculator.repository;

import com.example.LMS_EMICalculator.entity.EMI;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EMIRepo extends JpaRepository<EMI, Integer> {
}
