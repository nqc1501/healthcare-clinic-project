package com.example.healthcareservice.repository;

import com.example.healthcareservice.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<TestResult, Integer> {

    List<TestResult> findByPatientId(String patientId);
}
