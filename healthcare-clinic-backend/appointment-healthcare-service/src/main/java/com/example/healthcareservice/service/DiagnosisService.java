package com.example.healthcareservice.service;

import com.example.healthcareservice.model.Diagnosis;
import com.example.healthcareservice.dto.req.AppointmentRequest;
import com.example.responsehandling.payload.response.AppResponse;

import java.util.List;

public interface DiagnosisService {

    AppResponse getDiagnosisByPatientId(String patientId, AppointmentRequest request);

    AppResponse addDiagnosis(Diagnosis diagnosis);

    List<Diagnosis> getAllByAppointmentId(Integer id);
}
