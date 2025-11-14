package com.example.LMS_ActionService.repository;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "LMS-QueryService")
public interface LoanIDRepo {
}
