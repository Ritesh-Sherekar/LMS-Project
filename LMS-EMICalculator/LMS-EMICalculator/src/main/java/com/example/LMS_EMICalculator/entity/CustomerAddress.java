package com.example.LMS_EMICalculator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddress {
    @Id
    @SequenceGenerator(name = "cus_add_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cus_add_seq")
    private int id;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String pincode;
    private String country;

}
