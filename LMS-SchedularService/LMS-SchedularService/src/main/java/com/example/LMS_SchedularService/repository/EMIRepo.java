package com.example.LMS_SchedularService.repository;

import com.example.LMS_SchedularService.entity.EMI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EMIRepo extends JpaRepository<EMI, Integer> {
    @Query(value = "SELECT * FROM emi WHERE TRUNC(due_date) = TRUNC(SYSDATE) + 1", nativeQuery = true)
    List<EMI> findEmiDueTomorrow();
}

