package com.example.healthcareservice.service;

import com.example.healthcareservice.model.Symptom;
import com.example.responsehandling.payload.response.AppResponse;

import java.util.List;

public interface SymptomService {

    List<Symptom> getByPatientId(String patientId);

    AppResponse addSymptom(Symptom symptom);
}
