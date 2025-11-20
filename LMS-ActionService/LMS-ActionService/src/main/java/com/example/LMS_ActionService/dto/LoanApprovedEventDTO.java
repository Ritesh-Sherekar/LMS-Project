package com.example.LMS_ActionService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanApprovedEventDTO {
    private int loanId;
    private double loanAmount;
    private double annualInterestRate;
    private int tenureMonths;
    private String customerEmail;
    private String customerName;
    private String loanType;
}
