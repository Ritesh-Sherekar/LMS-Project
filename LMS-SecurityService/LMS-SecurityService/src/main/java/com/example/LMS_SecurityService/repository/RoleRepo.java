package com.example.LMS_SecurityService.repository;

import com.example.LMS_SecurityService.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(String roleName);
}
