package com.example.LMS_SchedularService.service;

import com.example.LMS_SchedularService.dto.UniversalDTO;
import com.example.LMS_SchedularService.template.EmiReminderEmailTemplate;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class SendMailService {
    @Autowired
    private JavaMailSender javaMailSender;

    // Send Mail Method
    @Async("asyncExecutor")
    public void sendMail(UniversalDTO customerInfo) throws MessagingException {
        String emiReminderHtml = EmiReminderEmailTemplate.getEmiReminderHtml(customerInfo.getFirstName(),
                customerInfo.getLoanId(),
                customerInfo.getLoanType(),
                customerInfo.getEmiAmount(),
                customerInfo.getDueDate());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setSubject("⏰ EMI Reminder: Payment Due on " + customerInfo.getDueDate() + " for Loan ID: " + customerInfo.getLoanId());
        mimeMessageHelper.setTo(customerInfo.getEmail());
        mimeMessageHelper.setText(emiReminderHtml,true);

        log.info("Sending Mail to the {}", customerInfo.getEmail());
        javaMailSender.send(mimeMessage);
    }
}
