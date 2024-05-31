package com.example.userservice.service.patient.impl;

import com.example.userservice.model.Patient;
import com.example.userservice.repository.PatientRepository;
import com.example.userservice.service.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository rPat;

    @Override
    public List<Patient> findAllPatients() {
        return rPat.findAll();
    }

    @Override
    public Patient findById(String id) {
        return rPat.findById(id).orElse(null);
    }

    private String generateId() {
        String randomUUID = UUID.randomUUID().toString();
        return "BN" + randomUUID.substring(0, 8);
    }
}
