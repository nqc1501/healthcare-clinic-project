package com.example.healthcareservice.repository;

import com.example.healthcareservice.model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Integer> {

    List<Symptom> findByPatientId(String patientId);
}
