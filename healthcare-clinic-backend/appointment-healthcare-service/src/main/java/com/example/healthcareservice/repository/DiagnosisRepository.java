package com.example.healthcareservice.repository;

import com.example.healthcareservice.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer> {

    List<Diagnosis> findByAppointmentId(Integer appointmentId);
}
