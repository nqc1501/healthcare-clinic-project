package com.example.userservice.service;

import com.example.responsehandling.payload.response.AppResponse;
import com.example.userservice.model.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> getAllPatient();

    Patient getById(String id);

    AppResponse addPatient(Patient patient);

    AppResponse updatePatient(Patient patient);

    AppResponse deletePatient(String id);
}
