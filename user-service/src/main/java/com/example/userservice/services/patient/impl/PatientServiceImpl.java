package com.example.userservice.services.patient.impl;

import com.example.userservice.models.Patient;
import com.example.userservice.repositories.PatientRepository;
import com.example.userservice.services.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository rPatient;

    @Override
    public List<Patient> findAllPatients() {
        return null;
    }

    private String generateId() {
        String randomUUID = UUID.randomUUID().toString();
        return "BN" + randomUUID.substring(0, 8);
    }
}
