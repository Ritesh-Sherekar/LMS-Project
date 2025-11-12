package com.example.LMS_ActionService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {
    private String loanType;
    private Double loanAmount;
    private Double interestRate;
    private Integer tenureMonths;
    private LocalDate applicationDate;
    private LocalDate approvalDate;
    private String loanStatus;
    private Double emiAmount;
    private Double totalPayableAmount;
    private Double remainingBalance;
}
