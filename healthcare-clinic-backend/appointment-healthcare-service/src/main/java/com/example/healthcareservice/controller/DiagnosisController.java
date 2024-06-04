package com.example.healthcareservice.controller;

import com.example.healthcareservice.model.Diagnosis;
import com.example.healthcareservice.payload.req.AppointmentRequest;
import com.example.healthcareservice.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class DiagnosisController {

    @Autowired
    DiagnosisService sDiagnosis;

    @GetMapping("/patient/{patientId}/diagnosis")
    public ResponseEntity<?> getDiagnosisByPatient(@PathVariable String patientId, @RequestBody AppointmentRequest request) {
        return ResponseEntity.ok(sDiagnosis.getDiagnosisByPatientId(patientId, request));
    }

    @PostMapping("/diagnosis")
    public ResponseEntity<?> addDiagnosis(@RequestBody Diagnosis diagnosis) {
        return ResponseEntity.ok(sDiagnosis.addDiagnosis(diagnosis));
    }
}
