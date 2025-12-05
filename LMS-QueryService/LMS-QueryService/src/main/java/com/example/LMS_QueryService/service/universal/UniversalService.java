package com.example.LMS_QueryService.service.universal;

import com.example.LMS_QueryService.dto.UniversalDTO;
import com.example.LMS_QueryService.entity.CustomerDetails;
import com.example.LMS_QueryService.entity.EMI;
import com.example.LMS_QueryService.entity.EmiPayment;
import com.example.LMS_QueryService.entity.Loan;
import com.example.LMS_QueryService.repository.CustomerDetailsRepo;
import com.example.LMS_QueryService.repository.EMIRepo;
import com.example.LMS_QueryService.repository.EmiPaymentRepo;
import com.example.LMS_QueryService.repository.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversalService {
    @Autowired
    private CustomerDetailsRepo customerDetailsRepo;

    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private EMIRepo emiRepo;

    @Autowired
    private EmiPaymentRepo emiPaymentRepo;

    // Get All Details of Customer
    public UniversalDTO getAllDetailsOfCustomer(int loanID){
        // Get Loan By ID
        Loan loan = loanRepo.findById(loanID).orElseThrow();

        // Get Customer Details By cus ID
        CustomerDetails byCustomerId = customerDetailsRepo.findByCustomerId(loan.getCustomerDetails().getCustomerId());

        // Get EMI By Loan ID
        EMI emi = emiRepo.findByLoanID(loanID).orElseThrow();

        // Get Emi Payment By Loan ID
        List<EmiPayment> byLoanID = emiPaymentRepo.findByLoanID(loanID);

        UniversalDTO universalDTO = new UniversalDTO();

        universalDTO.setCustomerId(byCustomerId.getCustomerId());
        universalDTO.setFirstName(byCustomerId.getFirstName());
        universalDTO.setLastName(byCustomerId.getLastName());
        universalDTO.setEmail(byCustomerId.getEmail());
        universalDTO.setDateOfBirth(byCustomerId.getDateOfBirth());
        universalDTO.setGender(byCustomerId.getGender());
        universalDTO.setPhoneNumber(byCustomerId.getPhoneNumber());
        universalDTO.setStatus(byCustomerId.getStatus());

        universalDTO.setLoanId(loan.getId());
        universalDTO.setLoanType(loan.getLoanType());
        universalDTO.setLoanAmount(loan.getLoanAmount());
        universalDTO.setTenureMonths(loan.getTenureMonths());
        universalDTO.setApplicationDate(loan.getApplicationDate());
        universalDTO.setApprovalDate(loan.getApprovalDate());
        universalDTO.setLoanStatus(loan.getLoanStatus());
        universalDTO.setTotalPayableAmount(loan.getTotalPayableAmount());
        universalDTO.setRemainingBalance(loan.getRemainingBalance());
        universalDTO.setEmploymentType(loan.getEmploymentType());
        universalDTO.setMonthlyIncome(loan.getMonthlyIncome());

        universalDTO.setEmiId(emi.getId());
        universalDTO.setEmiAmount(emi.getEmiAmount());
        universalDTO.setInterestRate(emi.getInterestRate());
        universalDTO.setMonthNumber(emi.getMonthNumber());
        universalDTO.setTotalInterest(emi.getTotalInterest());
        universalDTO.setEmiStatus(emi.getStatus());

        for (EmiPayment payment : byLoanID){
            universalDTO.setEmiPaymentId(payment.getId());
            universalDTO.setPaidAmount(payment.getPaidAmount());
            universalDTO.setPaymentDate(payment.getPaymentDate());
            universalDTO.setEmiPaymentStatus(universalDTO.getEmiPaymentStatus());
            universalDTO.setRemainingLoanAmount(payment.getRemainingLoanAmount());
            universalDTO.setRemainingMonthNumber(payment.getRemainingMonthNumber());
            universalDTO.setRemainingTotalPayableAmount(payment.getRemainingTotalPayableAmount());
            universalDTO.setDueDate(payment.getDueDate());
            universalDTO.setCreatedAt(payment.getCreatedAt());
        }

        return universalDTO;
    }
}
