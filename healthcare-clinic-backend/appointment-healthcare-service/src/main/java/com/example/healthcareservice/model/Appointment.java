package com.example.healthcareservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "t_appointment")
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "patient_id", nullable = false)
    private String patientId;
    @Column(name = "doctor_id", nullable = false)
    private String doctorId;
    private Date time;
}
