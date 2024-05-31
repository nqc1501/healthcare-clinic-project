package com.example.diagnosisservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "t_test_result")
@AllArgsConstructor
@NoArgsConstructor
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private float referenceValue;
    private float result;
    private String unit;
    @ManyToMany(mappedBy = "testResults")
    private Set<Diagnostic> diagnostics;
}
