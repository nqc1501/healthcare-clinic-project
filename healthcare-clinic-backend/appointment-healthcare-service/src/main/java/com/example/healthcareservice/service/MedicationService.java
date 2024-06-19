package com.example.healthcareservice.service;

import com.example.healthcareservice.model.item.Medication;
import com.example.healthcareservice.model.item.Supply;
import com.example.responsehandling.payload.response.AppResponse;

import java.util.List;

public interface MedicationService {

    List<Medication> getAllMedication();

    AppResponse addMedication(Medication medication);

    AppResponse updateMedication(Medication medication);

    AppResponse deleteMedication(Integer id);
}
