package com.example.userservice.controller;

import com.example.userservice.model.Patient;
import com.example.userservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    @Autowired
    PatientService sPatient;

    @GetMapping
    public ResponseEntity<?> getAllPatient() {
        return ResponseEntity.ok(sPatient.getAllPatient());
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<?> getAllPatientByStatus(@PathVariable String status) {
        return ResponseEntity.ok(sPatient.getByStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable String id) {
        return ResponseEntity.ok(sPatient.getById(id));
    }

    @GetMapping("/by-health-code/{healthCode}")
    public ResponseEntity<?> getByHealthCode(@PathVariable String healthCode) {
        return ResponseEntity.ok(sPatient.getByHealthCode(healthCode));
    }

    @PostMapping
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(sPatient.addPatient(patient));
    }

    @PutMapping
    public ResponseEntity<?> updatePatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(sPatient.updatePatient(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable String id) {
        return ResponseEntity.ok(sPatient.deletePatient(id));
    }
}
