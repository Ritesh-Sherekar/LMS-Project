package com.example.LMS_SecurityService.repository;

import com.example.LMS_SecurityService.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    Customer findByUsername(String userName);
}
