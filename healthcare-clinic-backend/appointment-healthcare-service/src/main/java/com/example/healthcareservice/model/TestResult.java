package com.example.healthcareservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Table(name = "t_test")
@Entity
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "patient_id", nullable = false)
    private String patientId;
    private String name;
    private float referenceValue;
    private float result;
    private String unit;
    @ManyToMany(mappedBy = "testResults")
    private Set<Diagnosis> diagnosis;
}
