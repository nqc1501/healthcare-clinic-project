package com.example.diagnosisservice.service;

import com.example.diagnosisservice.model.Diagnostic;
import com.example.diagnosisservice.payload.AppResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiagnosticService {

    List<Diagnostic> findAllDiagnostic();

    AppResponse addNewDiagnostic(Diagnostic request);
}
