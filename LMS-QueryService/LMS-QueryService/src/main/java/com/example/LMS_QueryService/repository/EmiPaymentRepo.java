package com.example.LMS_QueryService.repository;

import com.example.LMS_QueryService.entity.EmiPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmiPaymentRepo extends JpaRepository<EmiPayment, Integer> {
}
