package com.example.LMS_ActionService.repository;

import com.example.LMS_ActionService.configuration.FeignClientConfig;
import com.example.LMS_ActionService.dto.EmiPaymentDTO;
import com.example.LMS_ActionService.dto.LoanDTOForResponse;
import com.example.LMS_ActionService.entity.CustomerDetails;
import com.example.LMS_ActionService.entity.EMI;
import com.example.LMS_ActionService.entity.EmiPayment;
import com.example.LMS_ActionService.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "LMS-QueryService", configuration = FeignClientConfig.class)
public interface ClientLoanIDRepo {
    @GetMapping("/queryLoan/getLoanByID")
    Response<LoanDTOForResponse> getLoneByID(@RequestParam("id") int id);

    @GetMapping("/queryCustomerDetails/getCustomerDetailsByCustomerTableID")
    Response<CustomerDetails> getCustomerDetailsByCustomerTableID(@RequestParam("id") int id);

    @GetMapping("/queryCustomerDetails/getCustomerDetailsByCusID")
    Response<CustomerDetails> getCustomerDetailsByCusID(@RequestParam String customerId);

    @GetMapping("/queryEmi/getEmiByLoanID")
    Response<EMI> getEmiByLoanID(@RequestParam int loanID);

    @GetMapping("/queryEmiPayment/getEmiPaymentByLoanID")
    Response<EmiPayment> getEmiPaymentByLoanID(@RequestParam int loanID);

    @GetMapping("/queryEmiPayment/getLastEmiPayment")
    Response<EmiPayment> getLastEmiPayment();
}
