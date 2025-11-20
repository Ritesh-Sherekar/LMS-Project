package com.example.LMS_EmailService.service;

import com.example.LMS_EmailService.dto.LoanApprovedEventDTO;
import com.example.LMS_EmailService.response.LoanApprovalEmailTemplate;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    // Send mail to the customer for Loan Application has been approved
    public void sendEmailForLoanApproval(LoanApprovedEventDTO approvedEventDTO) throws MessagingException {
        String loanApprovalHtml = LoanApprovalEmailTemplate.getLoanApprovalHtml(approvedEventDTO.getCustomerName(),
                approvedEventDTO.getLoanId(),
                approvedEventDTO.getLoanType(),
                approvedEventDTO.getLoanAmount(),
                approvedEventDTO.getAnnualInterestRate(),
                approvedEventDTO.getTenureMonths());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setSubject("Your Loan ID:  " + approvedEventDTO.getLoanId() + " Has Been Successfully Approved 🚀");
        mimeMessageHelper.setTo(approvedEventDTO.getCustomerEmail());
        mimeMessageHelper.setText(loanApprovalHtml,true);

        javaMailSender.send(mimeMessage);
    }
}
