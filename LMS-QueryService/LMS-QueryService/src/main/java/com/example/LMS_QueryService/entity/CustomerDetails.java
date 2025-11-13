package com.example.LMS_QueryService.entity;

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

    // Relationship with Customer
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cus_id")
    private Customer customer;

    // Relationship with Address
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private CustomerAddress address;

    // Relationship with KYC
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "kyc_id")
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

