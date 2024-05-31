package com.example.userservice.controller;

import com.example.userservice.service.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    @Autowired
    PatientService sPat;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllPatients() {
        return ResponseEntity.ok(sPat.findAllPatients());
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok(sPat.findById(id));
    }
}
