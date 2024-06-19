package com.example.billingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Table(name = "t_bill")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String patientId;
    @Column(nullable = false)
    private Integer prescriptionId;
    private BigInteger amount;
    private String status;
}
