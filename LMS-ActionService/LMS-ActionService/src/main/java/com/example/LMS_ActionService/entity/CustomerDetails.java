package com.example.LMS_ActionService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {
    @Id
    @SequenceGenerator(name = "cus_det_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cus_det_seq")
    private int id;

    @Column(unique = true, nullable = false)
    private String customerId;   // CUS101

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    private String status;  // ACTIVE, INACTIVE, BLOCKED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "customerDetails", cascade = CascadeType.ALL)
    private CustomerAddress address;

    @OneToOne(mappedBy = "customerDetails", cascade = CascadeType.ALL)
    private CustomerKyc kycDetails;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

