package com.example.healthcareservice.service;

import com.example.healthcareservice.model.Diagnosis;
import com.example.healthcareservice.payload.req.AppointmentRequest;
import com.example.responsehandling.payload.response.AppResponse;

public interface DiagnosisService {

    AppResponse getDiagnosisByPatientId(String patientId, AppointmentRequest request);

    AppResponse addDiagnosis(Diagnosis diagnosis);
}
