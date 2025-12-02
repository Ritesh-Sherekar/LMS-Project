package com.example.LMS_ActionService.service.EmiPayment;

import com.example.LMS_ActionService.dto.EmiPaymentCompletedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmiEventProducer {
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    public void publishEmiCompleted(EmiPaymentCompletedDTO event) {
        kafkaTemplate.send("emi-completed-topic", event.getLoanID() + "", event);
    }
}
