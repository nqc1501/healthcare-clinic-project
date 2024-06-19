package com.example.userservice.service;

import com.example.responsehandling.payload.response.AppResponse;
import com.example.userservice.model.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> getAllPatient();

    List<Patient> getByStatus(String status);

    Patient getById(String id);

    Patient getByHealthCode(String healthCode);

    AppResponse addPatient(Patient patient);

    AppResponse updatePatient(Patient patient);

    AppResponse deletePatient(String id);
}
