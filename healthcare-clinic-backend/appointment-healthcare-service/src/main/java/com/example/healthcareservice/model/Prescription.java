package com.example.healthcareservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "t_prescription")
@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "diagnosis_id", referencedColumnName = "id")
    private Diagnosis diagnosis;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "prescription_id", referencedColumnName = "id")
    private List<PrescribedMedication> listMedication;
}
