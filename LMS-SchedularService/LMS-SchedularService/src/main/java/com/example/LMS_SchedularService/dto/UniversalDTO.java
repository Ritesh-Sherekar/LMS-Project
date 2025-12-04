package com.example.LMS_SchedularService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversalDTO {

    // Customer Details
    private String customerId;   // CUS101
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String email;
    private String phoneNumber;
    private String status;

    // Loan
    private int loanId;
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

    // EMI
    private Integer emiId;
    private Integer monthNumber;         // 1, 2, 3, ...
    private Double totalInterest;
    private String emiStatus;               // PENDING, PAID, LATE

    // EMI Payment
    private Integer emiPaymentId;
    private Double paidAmount;
    private LocalDate paymentDate;
    private String emiPaymentStatus;
    private Double remainingLoanAmount;
    private Integer remainingMonthNumber;
    private Double remainingTotalPayableAmount;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
}
