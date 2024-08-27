package com.example.healthcareservice.controller;

import com.example.healthcareservice.model.Prescription;
import com.example.healthcareservice.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/prescription")
public class PrescriptionController {

    @Autowired
    PrescriptionService sPrescription;

    @GetMapping("/find-by-diagnosis/{diagnosisId}")
    public ResponseEntity<?> getByDiagnosisId(@PathVariable Integer diagnosisId) {
        return ResponseEntity.ok(sPrescription.getByDiagnosisId(diagnosisId));
    }

    @PostMapping
    public ResponseEntity<?> addPrescription(@RequestBody Prescription prescription) {
        return ResponseEntity.ok(sPrescription.addPrescription(prescription));
    }
}
