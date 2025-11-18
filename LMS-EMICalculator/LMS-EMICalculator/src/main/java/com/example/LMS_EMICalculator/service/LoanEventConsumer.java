package com.example.LMS_EMICalculator.service;

import com.example.LMS_EMICalculator.dto.LoanApprovedEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoanEventConsumer {
    private final EMIService emiService;

    public LoanEventConsumer(EMIService emiService) {
        this.emiService = emiService;
    }

    @KafkaListener(topics = "loan-approved-topic", groupId = "emi-calculator-group")
    public void consumeLoanApprovedEvent(LoanApprovedEventDTO event) {
        log.info("Received Loan for EMI Calculation: {}", event);

        emiService.calculateEMI(event);
    }
}
