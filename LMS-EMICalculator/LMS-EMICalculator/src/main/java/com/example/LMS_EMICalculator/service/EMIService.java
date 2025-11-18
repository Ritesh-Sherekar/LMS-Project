package com.example.LMS_EMICalculator.service;

import com.example.LMS_EMICalculator.dto.LoanApprovedEventDTO;
import com.example.LMS_EMICalculator.dto.ResponseDTO;
import com.example.LMS_EMICalculator.entity.EMI;
import com.example.LMS_EMICalculator.repository.EMIRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EMIService {
    @Autowired
    private EMIRepo emiRepo;

    // Calculate EMI
    public ResponseDTO calculateEMI(LoanApprovedEventDTO loanApprovedEventDTO) {

        double P = loanApprovedEventDTO.getLoanAmount();
        double annualInterest = loanApprovedEventDTO.getAnnualInterestRate();
        int N = loanApprovedEventDTO.getTenureMonths();

        // Convert annual interest to monthly (as double)
        double R = (annualInterest / 12) / 100;

        ResponseDTO responseDTO = new ResponseDTO();

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
        emi1.setEmiAmount(emi);
        emi1.setTotalInterest(totalInterest);
        emi1.setTotalPayableAmount(totalPayable);
        emi1.setDueDate(LocalDate.now());
        emi1.setStatus("Pending");
        emi1.setCreatedAt(LocalDate.now());
        emi1.setUpdatedAt(null);

        emiRepo.save(emi1);

        responseDTO.setLoanID(loanApprovedEventDTO.getLoanId());
        responseDTO.setEmiAmount(round(emi));
        responseDTO.setTotalPayableAmount(round(totalPayable));
        responseDTO.setTotalInterest(round(totalInterest));
        System.out.println("Response DTO " + responseDTO);
        return responseDTO;
    }

    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0; // round to 2 decimals
    }
}
