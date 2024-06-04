package com.example.healthcareservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "t_symptom")
@Entity
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "patient_id", nullable = false)
    private String patientId;
    private String name;
}
