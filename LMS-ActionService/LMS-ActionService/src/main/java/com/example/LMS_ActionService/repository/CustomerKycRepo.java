package com.example.LMS_ActionService.repository;

import com.example.LMS_ActionService.entity.CustomerKyc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerKycRepo extends JpaRepository<CustomerKyc, Integer> {
}
