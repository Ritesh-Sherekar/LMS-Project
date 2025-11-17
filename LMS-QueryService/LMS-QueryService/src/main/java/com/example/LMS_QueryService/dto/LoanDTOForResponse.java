package com.example.LMS_QueryService.dto;

import com.example.LMS_QueryService.entity.CustomerDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTOForResponse {
    private int id;

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
    private String employmentType;
    private Double monthlyIncome;

    private String customerDetailsID;
}
