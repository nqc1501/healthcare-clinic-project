package com.example.healthcareservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Table(name = "t_diagnosis")
@Entity
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;
    private String conclusion;
    @ManyToMany
    @JoinTable(
            name = "t_diagnosis_test",
            joinColumns = @JoinColumn(name = "diagnosis_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id")
    )
    private Set<TestResult> testResults;
}
