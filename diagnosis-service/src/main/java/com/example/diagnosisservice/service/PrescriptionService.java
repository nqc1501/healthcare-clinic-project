package com.example.diagnosisservice.service;

import com.example.diagnosisservice.model.Prescription;
import com.example.diagnosisservice.payload.AppResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrescriptionService {

    List<Prescription> findAllPrescription();

    AppResponse addNewPrescription(Prescription request);
}
