package com.example.LMS_EMICalculator.service;

import com.example.LMS_EMICalculator.dto.LoanApprovedEventDTO;
import com.example.LMS_EMICalculator.entity.EMI;
import com.example.LMS_EMICalculator.entity.Loan;
import com.example.LMS_EMICalculator.repository.EMIRepo;
import com.example.LMS_EMICalculator.repository.LoanRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class EMIService {
    @Autowired
    private EMIRepo emiRepo;

    @Autowired
    private LoanRepo loanRepo;

    // Calculate EMI
    public void calculateEMI(LoanApprovedEventDTO loanApprovedEventDTO) {
        log.info("Requesting for calculate EMI base on {}", loanApprovedEventDTO);

        double P = loanApprovedEventDTO.getLoanAmount();
        double annualInterest = loanApprovedEventDTO.getAnnualInterestRate();
        int N = loanApprovedEventDTO.getTenureMonths();

        // Convert annual interest to monthly (as double)
        double R = (annualInterest / 12) / 100;

        double emi;

        // If interest rate is 0% (rare case)
        if (R == 0) {
            emi = P / N;
        } else {
            double numerator = P * R * Math.pow(1 + R, N);
            double denominator = Math.pow(1 + R, N) - 1;
            emi = numerator / denominator;
        }

        double totalPayable = emi * N;
        double totalInterest = totalPayable - P;

        EMI emi1 = new EMI();
        emi1.setLoanID(loanApprovedEventDTO.getLoanId());
        emi1.setLoanAmount(loanApprovedEventDTO.getLoanAmount());
        emi1.setInterestRate(loanApprovedEventDTO.getAnnualInterestRate());
        emi1.setMonthNumber(loanApprovedEventDTO.getTenureMonths());
        emi1.setEmiAmount(round(emi));
        emi1.setTotalInterest(round(totalInterest));
        emi1.setTotalPayableAmount(round(totalPayable));
        emi1.setDueDate(LocalDate.now().plusDays(1));
        emi1.setStatus("Pending");
        emi1.setCreatedAt(LocalDate.now());
        emi1.setUpdatedAt(null);

        log.info("Emi Calculate {}", emi1);

        emiRepo.save(emi1);

        Loan loan = loanRepo.findById(loanApprovedEventDTO.getLoanId()).orElseThrow(()-> new RuntimeException("Loan Not Found"));
        loan.setEmiAmount(round(emi));
        loan.setInterestRate(loan.getInterestRate());
        loan.setTotalPayableAmount(round(totalPayable));

        loanRepo.save(loan);
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0; // round to 2 decimals
    }
}
