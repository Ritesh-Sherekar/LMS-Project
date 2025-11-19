package com.example.LMS_ActionService.service.Loan;

import com.example.LMS_ActionService.dto.LoanApprovedEventDTO;
import com.example.LMS_ActionService.dto.LoanDTO;
import com.example.LMS_ActionService.dto.LoanDTOForResponse;
import com.example.LMS_ActionService.entity.Customer;
import com.example.LMS_ActionService.entity.CustomerDetails;
import com.example.LMS_ActionService.entity.Loan;
import com.example.LMS_ActionService.entity.LoanType;
import com.example.LMS_ActionService.enums.Status;
import com.example.LMS_ActionService.exception.InvalidApplicationStatusException;
import com.example.LMS_ActionService.repository.*;
import com.example.LMS_ActionService.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Service
@Slf4j
public class LoanService {
    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CustomerDetailsRepo customerDetailsRepo;

    @Autowired
    private LoanTypeRepo loanTypeRepo;

    @Autowired
    private ClientLoanIDRepo clientLoanIDRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LoanEventProducer loanEventProducer;

    // Apply For Loan
    public Loan applyLoan(LoanDTO loanDTO){
        log.info("Applying For Loan Request {}", loanDTO);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        log.info("Get Authentication Object From Security Context Holder with username {}", userName);

        Customer byUsername = customerRepo.findByUsername(userName);
        log.info("Get Customer From Username {}", byUsername);

        CustomerDetails byCusId = customerDetailsRepo.findByCustomer_Id(byUsername.getId());
        log.info("Get CustomerDetails By Customer ID {}", byCusId);

        LoanType byLoanType = loanTypeRepo.findByLoanType(loanDTO.getLoanType());
        log.info("Get LoneType {}", byLoanType);

        Loan loan = new Loan();
        loan.setLoanType(loanDTO.getLoanType());
        loan.setLoanAmount(loanDTO.getLoanAmount());
        loan.setTenureMonths(loanDTO.getTenureMonths());
        loan.setApplicationDate(LocalDate.now());
        loan.setLoanStatus(Status.PENDING.toString());
        loan.setEmploymentType(loanDTO.getEmploymentType());
        loan.setMonthlyIncome(loanDTO.getMonthlyIncome());
        loan.setInterestRate(byLoanType.getInterestRate());

        loan.setCustomerDetails(byCusId);

        log.info("Response After applying for Loan {}", loan);
        return loanRepo.save(loan);
    }

    // Cancel the Loan Application
    public String cancelLoanApplicationByID(@RequestParam int id){
        log.info("Request for Cancel Loan By Loan ID {}", id);

        Response<LoanDTOForResponse> loneByID = clientLoanIDRepo.getLoneByID(id);

        CustomerDetails byCustomerId = customerDetailsRepo.findByCustomer_Id(loneByID.getData().getId());

        // Not Working (But We can Use Like this To avoid Manual Mapping)
//        ObjectMapper objectMapper = new ObjectMapper();
//        Loan loan1 = objectMapper.convertValue(loneByID, Loan.class);
//        System.out.println("Loan Object " + loan1);
//        loan1.setLoanStatus(Status.CANCELED.toString());
//        System.out.println("After Changing Status ");

        Loan loan = new Loan();
        loan.setId(loneByID.getData().getId());
        loan.setLoanType(loneByID.getData().getLoanType());
        loan.setLoanAmount(loneByID.getData().getLoanAmount());
        loan.setInterestRate(loneByID.getData().getInterestRate());
        loan.setTenureMonths(loneByID.getData().getTenureMonths());
        loan.setApplicationDate(loneByID.getData().getApplicationDate());
        loan.setApprovalDate(loneByID.getData().getApprovalDate());
        loan.setLoanStatus(Status.CANCELED.toString());
        loan.setEmiAmount(loneByID.getData().getEmiAmount());
        loan.setTotalPayableAmount(loneByID.getData().getTotalPayableAmount());
        loan.setRemainingBalance(loneByID.getData().getRemainingBalance());
        loan.setEmploymentType(loneByID.getData().getEmploymentType());
        loan.setMonthlyIncome(loneByID.getData().getMonthlyIncome());
        loan.setCustomerDetails(byCustomerId);

        log.info("Cancel Loan Application By Changing Loan Status to CANCELED {}",loan);
        loanRepo.save(loan);

        return "Application Cancel Successfully";
    }

    // Manage Application Status of Lone Application
    public String manageStatus(int id, String status){
        log.info("Request for Managing Loan Application Status By Loan ID {}", id);

        Response<LoanDTOForResponse> loneByID = clientLoanIDRepo.getLoneByID(id);

        LoanDTOForResponse data = loneByID.getData();
        if (status.equalsIgnoreCase("APPROVED")){
            data.setLoanStatus(Status.APPROVED.toString());
            Loan loan = objectMapper.convertValue(data, Loan.class);
            loanRepo.save(loan);

            // Kafka Event
            LoanApprovedEventDTO event = new LoanApprovedEventDTO(
                    loneByID.getData().getId(),
                    loneByID.getData().getLoanAmount(),
                    loneByID.getData().getInterestRate(),
                    loneByID.getData().getTenureMonths(),
                    "example@gmail.com"
            );

            loanEventProducer.publishLoanApproved(event);

            return "Your Loan Application " + status;
        } else if (status.equalsIgnoreCase("REJECTED")) {
            data.setLoanStatus(Status.REJECTED.toString());
            Loan loan = objectMapper.convertValue(data, Loan.class);
            loanRepo.save(loan);
            return "Your Loan Application " + status;
        } else if (status.equalsIgnoreCase("CLOSED")) {
            data.setLoanStatus(Status.CLOSED.toString());
            Loan loan = objectMapper.convertValue(data, Loan.class);
            loanRepo.save(loan);
            return "Your Loan Application " + status;
        } else if (status.equalsIgnoreCase("CANCELED")) {
            data.setLoanStatus(Status.CANCELED.toString());
            Loan loan = objectMapper.convertValue(data, Loan.class);
            loanRepo.save(loan);
            return "Your Loan Application " + status;
        } else {
            throw new InvalidApplicationStatusException("Status Not Valid!");
        }
    }

}
