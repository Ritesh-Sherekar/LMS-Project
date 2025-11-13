package com.example.LMS_QueryService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerKyc {
    @Id
    @SequenceGenerator(name = "cus_kyc_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cus_kyc_seq")
    private int id;

    @Column(unique = true)
    private String aadhaarNumber;

    @Column(unique = true)
    private String panNumber;

    private String kycStatus; // PENDING, VERIFIED, REJECTED

}
