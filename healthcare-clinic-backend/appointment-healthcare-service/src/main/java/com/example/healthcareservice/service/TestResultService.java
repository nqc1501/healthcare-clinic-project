package com.example.healthcareservice.service;

import com.example.healthcareservice.model.TestResult;

import java.util.List;

public interface TestResultService {

    List<TestResult> getTestResultByPatientId(String patientId);
}
