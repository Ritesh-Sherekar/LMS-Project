package com.example.LMS_ActionService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "Loan",
        indexes = {
                @Index(name = "inx_loan_status", columnList = "loanStatus")
        }
)
public class Loan {
    @Id
    @SequenceGenerator(name = "loan_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_seq")
    private int id;

    private String loanType;          // e.g. "Personal Loan", "Home Loan"
    private Double loanAmount;        // Amount requested
    private Double interestRate;      // e.g. 10.5
    private Integer tenureMonths;     // e.g. 12 or 24
    private LocalDate applicationDate;
    private LocalDate approvalDate;
    private String loanStatus;        // PENDING, APPROVED, REJECTED, CLOSED
    private Double emiAmount;         // Calculated after approval
    private Double totalPayableAmount;
    private Double remainingBalance;
    private String employmentType;
    private Double monthlyIncome;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(
        name = "customerId",                // FK column in Loan table
        referencedColumnName = "customerId", // Maps to CustomerDetails.customerId
        foreignKey = @ForeignKey(name = "FK_LOAN_CUSTOMER_DETAILS")
)
    private CustomerDetails customerDetails;
}
