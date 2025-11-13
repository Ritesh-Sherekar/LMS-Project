package com.example.LMS_QueryService.repository;

import com.example.LMS_QueryService.entity.CustomerKyc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerKycRepo extends JpaRepository<CustomerKyc, Integer> {
}
