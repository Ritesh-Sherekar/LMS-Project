package com.example.LMS_SchedularService.service;

import com.example.LMS_SchedularService.dto.UniversalDTO;
import com.example.LMS_SchedularService.entity.EMI;
import com.example.LMS_SchedularService.repository.ClientRepo;
import com.example.LMS_SchedularService.repository.EMIRepo;
import com.example.LMS_SchedularService.response.Response;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedularService {
    @Autowired
    private EMIRepo emiRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private SendMailService sendMailService;

    // Send Remainder mail To Every Customer Before Due date
   // @Scheduled(cron = "0 0 10 * * *")  // Runs every day at 10 AM
    @Scheduled(cron = "0 */5 * * * *")
    public void sendRemainderMailSchedular() throws MessagingException {
        List<EMI> emiDueTomorrowCustomer = emiRepo.findEmiDueTomorrow();
        System.out.println("EMI " + emiDueTomorrowCustomer);

        for (EMI emi : emiDueTomorrowCustomer){
            Integer loanID = emi.getLoanID();
            System.out.println("Loan ID " + loanID);
            Response<UniversalDTO> allDetailsOfCustomer = clientRepo.getAllDetailsOfCustomer(loanID);
            UniversalDTO customerData = allDetailsOfCustomer.getData();
            System.out.println("Customer Data " + customerData);

            sendMailService.sendMail(customerData);
        }

    }
}
