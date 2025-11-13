package com.example.LMS_QueryService.repository;

import com.example.LMS_QueryService.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerDetailsRepo extends JpaRepository<CustomerDetails, Integer> {
    @Query("SELECT o FROM CustomerDetails o WHERE o.customer.id = :customerId")
    CustomerDetails findByCustomer_Id(@Param("customerId") int customerId);

    CustomerDetails findByCustomerId(String customerId);
}
