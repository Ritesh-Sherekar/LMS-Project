package com.example.LMS_ActionService.repository;

import com.example.LMS_ActionService.configuration.FeignClientConfig;
import com.example.LMS_ActionService.dto.LoanDTOForResponse;
import com.example.LMS_ActionService.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "LMS-QueryService", configuration = FeignClientConfig.class)
public interface ClientLoanIDRepo {
    @GetMapping("/queryLoan/getLoanByID")
    Response<LoanDTOForResponse> getLoneByID(@RequestParam("id") int id);
}
