package com.example.healthcareservice.controller;

import com.example.healthcareservice.model.Symptom;
import com.example.healthcareservice.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/symptom")
public class SymptomController {

    @Autowired
    SymptomService sSymptom;

    @GetMapping
    public ResponseEntity<?> getByPatientId(@RequestParam String patientId) {
        return ResponseEntity.ok(sSymptom.getByPatientId(patientId));
    }

    @PostMapping
    public ResponseEntity<?> addSymptom(@RequestParam String patientId, @RequestBody List<Symptom> listSymptom) {
        return ResponseEntity.ok(sSymptom.addSymptom(patientId, listSymptom));
    }
}
