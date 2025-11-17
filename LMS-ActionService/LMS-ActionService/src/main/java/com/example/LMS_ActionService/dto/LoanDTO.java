package com.example.LMS_ActionService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {
    private String loanType;
    private Double loanAmount;
    private Integer tenureMonths;
    private String employmentType;
    private double monthlyIncome;
}
