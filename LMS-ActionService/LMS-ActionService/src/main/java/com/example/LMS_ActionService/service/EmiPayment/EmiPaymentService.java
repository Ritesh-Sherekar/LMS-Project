package com.example.LMS_ActionService.service.EmiPayment;

import com.example.LMS_ActionService.dto.EmiPaymentDTO;
import com.example.LMS_ActionService.entity.EMI;
import com.example.LMS_ActionService.entity.EmiPayment;
import com.example.LMS_ActionService.enums.EmiStatus;
import com.example.LMS_ActionService.repository.ClientLoanIDRepo;
import com.example.LMS_ActionService.repository.EmiPaymentRepo;
import com.example.LMS_ActionService.response.Response;
import com.example.LMS_ActionService.service.WebClient.EmiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class EmiPaymentService {
    @Autowired
    private EmiPaymentRepo emiPaymentRepo;

//    @Autowired
//    private EmiClient emiClient;

    @Autowired
    private ClientLoanIDRepo clientLoanIDRepo;

    // Make Payment
    public EmiPayment makePayment(EmiPaymentDTO emiPaymentDTO){
        Response<EMI> emiByLoanID = clientLoanIDRepo.getEmiByLoanID(emiPaymentDTO.getLoanID());
        EMI emi = emiByLoanID.getData();

        Response<EmiPayment> emiPaymentByLoanID = clientLoanIDRepo.getEmiPaymentByLoanID(emiPaymentDTO.getLoanID());

        if (emiPaymentByLoanID.getData() == null){
            Double remainingLoanAmount = emi.getTotalPayableAmount() - emiPaymentDTO.getPaidAmount();
            Integer remainingMonthNumber = emi.getMonthNumber() - 1;

            EmiPayment emiPayment = new EmiPayment();

            emiPayment.setLoanID(emiPaymentDTO.getLoanID());
            emiPayment.setPaidAmount(emiPaymentDTO.getPaidAmount());
            emiPayment.setPaymentDate(LocalDate.now());
            emiPayment.setStatus(EmiStatus.PAID.toString());
            emiPayment.setRemainingLoanAmount(remainingLoanAmount);
            emiPayment.setRemainingMonthNumber(remainingMonthNumber);
            emiPayment.setRemainingTotalPayableAmount(remainingLoanAmount);
            emiPayment.setDueDate(emi.getDueDate());
            emiPayment.setCreatedAt(LocalDateTime.now());

            return emiPaymentRepo.save(emiPayment);
        }else {

            Response<EmiPayment> lastEmiPayment = clientLoanIDRepo.getLastEmiPayment();
            EmiPayment emiPaymentData = lastEmiPayment.getData();

            Double remainingLoanAmount = emiPaymentData.getRemainingLoanAmount() - emiPaymentDTO.getPaidAmount();
            Integer remainingMonthNumber = emiPaymentData.getRemainingMonthNumber() - 1;

            EmiPayment emiPayment = new EmiPayment();

            emiPayment.setLoanID(emiPaymentDTO.getLoanID());
            emiPayment.setPaidAmount(emiPaymentDTO.getPaidAmount());
            emiPayment.setPaymentDate(LocalDate.now());
            emiPayment.setStatus(EmiStatus.PAID.toString());
            emiPayment.setRemainingLoanAmount(remainingLoanAmount);
            emiPayment.setRemainingMonthNumber(remainingMonthNumber);
            emiPayment.setRemainingTotalPayableAmount(remainingLoanAmount);
            emiPayment.setDueDate(emiPaymentData.getDueDate());
            emiPayment.setCreatedAt(LocalDateTime.now());

            return emiPaymentRepo.save(emiPayment);
        }
    }
}
