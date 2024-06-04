package com.example.healthcareservice.controller;

import com.example.healthcareservice.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class TestController {

    @Autowired
    TestResultService sTest;

    @GetMapping("/patient/{patientId}/test-result")
    public ResponseEntity<?> getTestResultByPatientId(@PathVariable String patientId) {
        return ResponseEntity.ok(sTest.getTestResultByPatientId(patientId));
    }
}
