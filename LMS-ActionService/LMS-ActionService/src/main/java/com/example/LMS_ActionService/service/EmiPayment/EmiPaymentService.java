package com.example.LMS_ActionService.service.EmiPayment;

import com.example.LMS_ActionService.dto.EmiPaymentCompletedDTO;
import com.example.LMS_ActionService.dto.EmiPaymentDTO;
import com.example.LMS_ActionService.dto.LoanDTOForResponse;
import com.example.LMS_ActionService.entity.CustomerDetails;
import com.example.LMS_ActionService.entity.EMI;
import com.example.LMS_ActionService.entity.EmiPayment;
import com.example.LMS_ActionService.enums.EmiStatus;
import com.example.LMS_ActionService.repository.ClientLoanIDRepo;
import com.example.LMS_ActionService.repository.EmiPaymentRepo;
import com.example.LMS_ActionService.response.Response;
import com.example.LMS_ActionService.service.emi.EmiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmiPaymentService {
    @Autowired
    private EmiPaymentRepo emiPaymentRepo;

//    @Autowired
//    private EmiClient emiClient;

    @Autowired
    private ClientLoanIDRepo clientLoanIDRepo;

    @Autowired
    private EmiService emiService;

    @Autowired
    private EmiEventProducer emiEventProducer;

    // Make Payment
    public EmiPayment makePayment(EmiPaymentDTO emiPaymentDTO) {
        log.info("Request For MakePayment {}", emiPaymentDTO);

        log.info("Requesting For emi payment by lone id {}", emiPaymentDTO.getLoanID());
        Response<EMI> emiByLoanID = clientLoanIDRepo.getEmiByLoanID(emiPaymentDTO.getLoanID());
        EMI emi = emiByLoanID.getData();
        log.info("Response For EMI {}", emiByLoanID);

        log.info("Requesting For EMI Payment By Loan ID {}", emiPaymentDTO.getLoanID());
        Response<List<EmiPayment>> emiPaymentByLoanID =
                clientLoanIDRepo.getEmiPaymentByLoanID(emiPaymentDTO.getLoanID());

        List<EmiPayment> previousPayments = emiPaymentByLoanID.getData();
        log.info("Response For EMI Payment {}", emiPaymentByLoanID);

        boolean isFirstPayment = (previousPayments == null || previousPayments.isEmpty());

        if (isFirstPayment) {
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

            log.info("Saving First EMI Transaction {} of Loan ID {}", emiPayment, emiPaymentDTO.getLoanID());
            return emiPaymentRepo.save(emiPayment);
        }

        log.info("Requesting For Last EMI Payment By Loan ID {}", emiPaymentDTO.getLoanID());
        Response<EmiPayment> lastEmiPaymentByLoanID = clientLoanIDRepo.getLastEmiPaymentByLoanID(emiPaymentDTO.getLoanID());
        EmiPayment emiPaymentData = lastEmiPaymentByLoanID.getData();
        log.info("Response For Last EMI Payment {}", lastEmiPaymentByLoanID);

        // If Total Month is equal to 0, then change the EMI Status
        if (emiPaymentData.getRemainingMonthNumber() == 1 && emiPaymentData.getRemainingTotalPayableAmount() <= 0.0){
            log.info("Updating the EMI Status");
            emiService.updateEmiStatus(emiPaymentDTO.getLoanID());

            log.info("Requesting For Loan By Loan ID");
            Response<LoanDTOForResponse> loneByID = clientLoanIDRepo.getLoneByID(emiPaymentDTO.getLoanID());
            LoanDTOForResponse loanData = loneByID.getData();
            log.info("Response For Loan ID {} id {}", emiPaymentDTO.getLoanID(), loanData);

            log.info("Requesting For Customer Details of Cus ID {}", loanData.getCustomerDetailsID());
            Response<CustomerDetails> customerDetailsByCusID = clientLoanIDRepo.getCustomerDetailsByCusID(loanData.getCustomerDetailsID());
            CustomerDetails customerDetailsData = customerDetailsByCusID.getData();
            log.info("Response For Cus ID {} id {}", loanData.getCustomerDetailsID(), customerDetailsData);

            // Kafka Event
            EmiPaymentCompletedDTO completedDTO = new EmiPaymentCompletedDTO();
            completedDTO.setLoanID(emiPaymentDTO.getLoanID());
            completedDTO.setEmiID(emi.getId());
            completedDTO.setEmiStatus(EmiStatus.PAID.toString());
            completedDTO.setTotalPayableAmount(emi.getTotalPayableAmount());
            completedDTO.setCustomerName(customerDetailsData.getFirstName());
            completedDTO.setCustomerEmail(customerDetailsData.getEmail());
            completedDTO.setLoanType(loanData.getLoanType());
            completedDTO.setTotalInterest(emi.getTotalInterest());

            log.info("Sending CompleteDto object to the kafka Topic {}", completedDTO);
            emiEventProducer.publishEmiCompleted(completedDTO);
        }

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

        log.info("Saving Each Emi Payment Transaction {}", emiPayment);

        return emiPaymentRepo.save(emiPayment);
    }
}
