package com.example.diagnosisservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "t_diagnostic")
@AllArgsConstructor
@NoArgsConstructor
public class Diagnostic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer patientId;
    private Integer doctorId;
    private String conclusion;
    @ManyToMany
    @JoinTable(
            name = "t_diagnostic_test",
            joinColumns = @JoinColumn(name = "diagnostic_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id")
    )
    private Set<TestResult> testResults;
}
