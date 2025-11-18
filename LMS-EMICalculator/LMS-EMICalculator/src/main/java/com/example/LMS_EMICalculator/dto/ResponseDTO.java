package com.example.LMS_EMICalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private Integer loanID;
    private Double emiAmount;
    private Double totalInterest;
    private Double totalPayableAmount;
}
