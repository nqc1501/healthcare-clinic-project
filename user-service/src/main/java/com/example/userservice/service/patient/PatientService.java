package com.example.userservice.service.patient;

import com.example.userservice.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {

    List<Patient> findAllPatients();

    Patient findById(String id);
}
