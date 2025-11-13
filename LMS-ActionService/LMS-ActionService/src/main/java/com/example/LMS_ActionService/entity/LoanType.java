package com.example.LMS_ActionService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanType {
    @Id
    @SequenceGenerator(name = "loan_type_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "loan_type_seq")
    private int id;
    private String loanType;
    private Double interestRate;
}
