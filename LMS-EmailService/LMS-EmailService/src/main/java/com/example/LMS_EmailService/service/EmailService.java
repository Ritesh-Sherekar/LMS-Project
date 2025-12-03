package com.example.LMS_EmailService.service;

import com.example.LMS_EmailService.dto.EmiPaymentCompletedDTO;
import com.example.LMS_EmailService.dto.LoanApprovedEventDTO;
import com.example.LMS_EmailService.response.EmiCompletedEmailTemplate;
import com.example.LMS_EmailService.response.LoanApprovalEmailTemplate;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
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

        log.info("Sending Mail to the {}", approvedEventDTO.getCustomerEmail());
        javaMailSender.send(mimeMessage);
    }

    // Sending mail to the Customer for Emi Completed
    public void sendEmailForEmiCompleted(EmiPaymentCompletedDTO completedDTO) throws MessagingException {
        String emiCompletedHtml = EmiCompletedEmailTemplate.getEmiCompletedHtml(completedDTO.getLoanID(),
                completedDTO.getEmiID(),
                completedDTO.getCustomerName(),
                completedDTO.getLoanType(),
                completedDTO.getEmiStatus(),
                completedDTO.getTotalInterest(),
                completedDTO.getTotalPayableAmount());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setSubject("Your Loan EMI Of ID:  " + completedDTO.getLoanID() + " Has Been Successfully Completed 🚀");
        mimeMessageHelper.setTo(completedDTO.getCustomerEmail());
        mimeMessageHelper.setText(emiCompletedHtml,true);

        log.info("Sending EMI Completed Mail to the {}", completedDTO.getCustomerEmail());
        javaMailSender.send(mimeMessage);
    }
}
