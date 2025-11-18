package com.example.LMS_ActionService.service.Loan;

import com.example.LMS_ActionService.dto.LoanApprovedEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoanEventProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishLoanApproved(LoanApprovedEventDTO event) {
        kafkaTemplate.send("loan-approved-topic", event.getLoanId() + "", event);
    }
}
