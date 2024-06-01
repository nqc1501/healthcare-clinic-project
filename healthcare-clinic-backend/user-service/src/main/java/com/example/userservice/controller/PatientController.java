package com.example.userservice.controller;

import com.example.userservice.model.Patient;
import com.example.userservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    @Autowired
    PatientService sPatient;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatient() {
        return ResponseEntity.ok(sPatient.getAllPatient());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable String id) {
        return ResponseEntity.ok(sPatient.getById(id));
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
