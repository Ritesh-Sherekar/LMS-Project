package com.example.LMS_EmailService.service;

import com.example.LMS_EmailService.dto.LoanApprovedEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    // Send mail to the customer for Loan Application has been approved
    public void sendEmailForLoanApproval(LoanApprovedEventDTO approvedEventDTO){

    }
}
