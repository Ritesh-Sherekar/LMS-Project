package com.example.LMS_EmailService.service;

import com.example.LMS_EmailService.dto.LoanApprovedEventDTO;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoanEventConsumer {
    private final EmailService emiService;

    public LoanEventConsumer(EmailService emiService) {
        this.emiService = emiService;
    }

    @KafkaListener(topics = "loan-approved-topic", groupId = "email-sending-group", containerFactory = "loanApprovalKafkaListenerContainerFactory")
    public void consumeLoanApprovedEvent(LoanApprovedEventDTO event) throws MessagingException {
        log.info("Received Loan for EMI Calculation: {}", event);

        emiService.sendEmailForLoanApproval(event);
    }
}
