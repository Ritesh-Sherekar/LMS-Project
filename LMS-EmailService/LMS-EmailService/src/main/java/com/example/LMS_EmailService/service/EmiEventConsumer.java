package com.example.LMS_EmailService.service;


import com.example.LMS_EmailService.dto.EmiPaymentCompletedDTO;
import com.example.LMS_EmailService.dto.LoanApprovedEventDTO;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmiEventConsumer {
    private final EmailService emiService;

    public EmiEventConsumer(EmailService emiService) {
        this.emiService = emiService;
    }

    @KafkaListener(topics = "emi-completed-topic", groupId = "emi-completed-email-sending-group", containerFactory = "emiCompletedKafkaListenerContainerFactory")
    public void consumeEmiCompleted(EmiPaymentCompletedDTO event) throws MessagingException {
        log.info("Received Details for EMI Completed: {}", event);

        emiService.sendEmailForEmiCompleted(event);
    }
}
