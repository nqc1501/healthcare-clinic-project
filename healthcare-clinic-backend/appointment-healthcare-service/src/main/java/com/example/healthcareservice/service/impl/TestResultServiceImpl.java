package com.example.healthcareservice.service.impl;

import com.example.healthcareservice.model.TestResult;
import com.example.healthcareservice.repository.TestRepository;
import com.example.healthcareservice.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestResultServiceImpl implements TestResultService {

    @Autowired
    TestRepository rTest;

    @Override
    public List<TestResult> getTestResultByPatientId(String patientId) {
        return rTest.findByPatientId(patientId);
    }
}
