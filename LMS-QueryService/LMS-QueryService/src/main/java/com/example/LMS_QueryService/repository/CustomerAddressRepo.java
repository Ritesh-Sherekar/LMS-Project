package com.example.LMS_QueryService.repository;

import com.example.LMS_QueryService.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepo extends JpaRepository<CustomerAddress, Integer> {
}
