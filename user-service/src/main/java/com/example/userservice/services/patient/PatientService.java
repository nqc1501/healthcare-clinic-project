package com.example.userservice.services.patient;

import com.example.userservice.models.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
    List<Patient> findAllPatients();
}
