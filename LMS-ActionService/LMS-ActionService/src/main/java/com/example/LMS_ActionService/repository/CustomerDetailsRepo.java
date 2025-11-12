package com.example.LMS_ActionService.repository;

import com.example.LMS_ActionService.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerDetailsRepo extends JpaRepository<CustomerDetails, Integer> {
    @Query("SELECT o FROM CustomerDetails o WHERE o.customer.id = :customerId")
    CustomerDetails findByCustomer_Id(@Param("customerId") int customerId);
}
