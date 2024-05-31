package com.example.diagnosisservice.repository;

import com.example.diagnosisservice.model.Diagnostic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic, Integer> {
}
