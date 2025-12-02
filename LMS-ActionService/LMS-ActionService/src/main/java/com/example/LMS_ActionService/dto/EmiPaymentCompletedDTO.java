package com.example.LMS_ActionService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmiPaymentCompletedDTO {
    private int loanID;
    private int emiID;
    private String customerEmail;
    private String customerName;
//    private String loanType;
    private String emiStatus;
    private Double totalInterest;
    private Double totalPayableAmount;
}
