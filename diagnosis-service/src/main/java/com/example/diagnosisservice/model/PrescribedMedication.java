package com.example.diagnosisservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "t_prescribed_medication")
@AllArgsConstructor
@NoArgsConstructor
public class PrescribedMedication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer medicationId;
    private Integer quantity;
    private String dosage;
    private String note;
}
