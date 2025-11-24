package com.example.LMS_ActionService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmiPaymentDTO {
    private Integer loanID;
    private Double paidAmount;
}
