package com.example.healthcareservice.model;

import com.example.healthcareservice.model.item.Medication;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "t_prescribed")
@Entity
public class PrescribedMedication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "medication_id", referencedColumnName = "id")
    private Medication medication;
    private Integer quantity;
    private String dosage;
    private String note;
}
