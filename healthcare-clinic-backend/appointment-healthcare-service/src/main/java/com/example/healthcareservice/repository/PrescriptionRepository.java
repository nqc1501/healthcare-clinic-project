package com.example.healthcareservice.repository;

import com.example.healthcareservice.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

    List<Prescription> findByDiagnosisId(Integer diagnosisId);
}
