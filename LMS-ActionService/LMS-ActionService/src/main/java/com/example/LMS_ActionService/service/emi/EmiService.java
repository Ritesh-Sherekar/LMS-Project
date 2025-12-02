package com.example.LMS_ActionService.service.emi;

import com.example.LMS_ActionService.entity.EMI;
import com.example.LMS_ActionService.enums.EmiStatus;
import com.example.LMS_ActionService.repository.ClientLoanIDRepo;
import com.example.LMS_ActionService.repository.EMIRepo;
import com.example.LMS_ActionService.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmiService {
    @Autowired
    private EMIRepo emiRepo;

    @Autowired
    private ClientLoanIDRepo clientLoanIDRepo;

    // Update EMI Status
    public EMI updateEmiStatus(int loanID){
        Response<EMI> emiByLoanID = clientLoanIDRepo.getEmiByLoanID(loanID);
        EMI emiByLoanIDData = emiByLoanID.getData();

        emiByLoanIDData.setStatus(EmiStatus.PAID.toString());
        emiByLoanIDData.setUpdatedAt(LocalDate.now());

        return emiRepo.save(emiByLoanIDData);
    }
}
