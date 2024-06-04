package com.example.healthcareservice.repository;

import com.example.healthcareservice.model.Appointment;
import com.example.healthcareservice.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer> {

    Optional<Diagnosis> findByAppointmentId(Integer appointmentId);
}
