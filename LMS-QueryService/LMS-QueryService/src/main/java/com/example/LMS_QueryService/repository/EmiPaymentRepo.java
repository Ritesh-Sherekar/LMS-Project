package com.example.LMS_QueryService.repository;

import com.example.LMS_QueryService.entity.EmiPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmiPaymentRepo extends JpaRepository<EmiPayment, Integer> {
    EmiPayment findByLoanID(int loanID);

    @Query(value = "SELECT * FROM (SELECT * FROM emi_payment ORDER BY created_at DESC) WHERE ROWNUM = 1",
            nativeQuery = true)
    EmiPayment findLastEmiPayment();


}
