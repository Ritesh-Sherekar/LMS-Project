package com.example.LMS_QueryService.repository;

import com.example.LMS_QueryService.entity.EmiPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmiPaymentRepo extends JpaRepository<EmiPayment, Integer> {
    List<EmiPayment> findByLoanID(int loanID);

    @Query(value = "SELECT * FROM (SELECT * FROM emi_payment ORDER BY created_at DESC) WHERE ROWNUM = 1",
            nativeQuery = true)
    EmiPayment findLastEmiPayment();

    @Query(value = "SELECT * FROM (" +
            "   SELECT e.*, ROW_NUMBER() OVER (ORDER BY e.created_at DESC) AS rn " +
            "   FROM emi_payment e " +
            "   WHERE e.LOANID = :loanID" +
            ") WHERE rn = 1",
            nativeQuery = true)
    EmiPayment findLastEmiPaymentByLoanId(@Param("loanID") int loanID);






}
