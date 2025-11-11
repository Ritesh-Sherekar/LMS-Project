package com.example.LMS_ActionService.repository;

import com.example.LMS_ActionService.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepo extends JpaRepository<CustomerAddress, Integer> {
}
