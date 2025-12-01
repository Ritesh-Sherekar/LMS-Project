package com.example.LMS_QueryService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmiPayment {
    @Id
    @SequenceGenerator(name = "emi_pay_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emi_pay_seq")
    private Integer id;

    private Integer loanID;
    private Double paidAmount;
    private LocalDate paymentDate;
    private String status;

    private Double remainingLoanAmount;
    private Integer remainingMonthNumber;
    private Double remainingTotalPayableAmount;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
}
