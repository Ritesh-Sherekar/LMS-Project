package com.example.LMS_ActionService.repository;

import com.example.LMS_ActionService.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailsRepo extends JpaRepository<CustomerDetails, Integer> {
}
