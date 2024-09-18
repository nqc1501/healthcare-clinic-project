package com.example.userservice.repository;

import com.example.userservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

    List<Patient> findByStatus(String status);

    Optional<Patient> findByHealthInsuranceCode(String healthInsuranceCode);
}
