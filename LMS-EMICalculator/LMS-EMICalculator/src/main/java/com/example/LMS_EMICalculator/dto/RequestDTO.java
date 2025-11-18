package com.example.LMS_EMICalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private Integer loanID;
    private Double loanAmount;
    private Double interestRate;
    private Integer monthNumber;
}
