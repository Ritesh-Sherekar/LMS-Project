package com.example.LMS_QueryService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EMI {
    @Id
    @SequenceGenerator(name = "emi_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emi_seq")
    private Integer id;

    private Integer loanID;
    private Double loanAmount;
    private Double interestRate;
    private Integer monthNumber;         // 1, 2, 3, ...
    private Double emiAmount;            // Fixed EMI per month
    private Double totalInterest;
    private Double totalPayableAmount;
    private LocalDate dueDate;           // EMI due date
    private String status;               // PENDING, PAID, LATE
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
