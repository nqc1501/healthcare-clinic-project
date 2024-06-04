package com.example.healthcareservice.controller;

import com.example.healthcareservice.model.Symptom;
import com.example.healthcareservice.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class SymptomController {

    @Autowired
    SymptomService sSymptom;

    @GetMapping("/patient/{patientId}/symptom")
    public ResponseEntity<?> getByPatientId(@PathVariable String patientId) {
        return ResponseEntity.ok(sSymptom.getByPatientId(patientId));
    }

    @PostMapping("/symptom")
    public ResponseEntity<?> addSymptom(@RequestBody Symptom symptom) {
        return ResponseEntity.ok(sSymptom.addSymptom(symptom));
    }
}
