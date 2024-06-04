package com.example.healthcareservice.service;

import com.example.healthcareservice.model.Prescription;
import com.example.responsehandling.payload.response.AppResponse;

import java.util.List;

public interface PrescriptionService {

    List<Prescription> getByDiagnosisId(Integer diagnosisId);

    AppResponse addPrescription(Prescription prescription);
}
