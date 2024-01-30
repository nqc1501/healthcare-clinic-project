package com.example.userservice.controllers;

import com.example.userservice.services.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    @Autowired
    PatientService sPatient;

    @GetMapping("/find-all-patients")
    public ResponseEntity<?> findAllPatients() {
        return ResponseEntity.ok(sPatient.findAllPatients());
    }
}
