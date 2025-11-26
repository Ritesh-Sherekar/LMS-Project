package com.example.LMS_ActionService.service.EmiPayment;

import com.example.LMS_ActionService.dto.EmiPaymentDTO;
import com.example.LMS_ActionService.entity.EMI;
import com.example.LMS_ActionService.repository.EmiPaymentRepo;
import com.example.LMS_ActionService.response.Response;
import com.example.LMS_ActionService.service.WebClient.EmiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EmiPaymentService {
    @Autowired
    private EmiPaymentRepo emiPaymentRepo;

    @Autowired
    private EmiClient emiClient;

    // Make Payment
    public Response<EMI> makePayment(EmiPaymentDTO emiPaymentDTO){
        System.out.println("requet :- "+emiPaymentDTO);
        Response<EMI> emiByLoanID = emiClient.getEmiByLoanID(emiPaymentDTO.getLoanID());

        System.out.println("Emi By Loan ID "+emiByLoanID);
        return emiByLoanID;
    }
}
