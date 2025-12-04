package com.example.LMS_SchedularService.repository;

import com.example.LMS_SchedularService.dto.UniversalDTO;
import com.example.LMS_SchedularService.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "LMS-QueryService")
public interface ClientRepo {
    @GetMapping("/queryUniversal/getUniversal")
    Response<UniversalDTO> getAllDetailsOfCustomer(@RequestParam int loanID);
}
