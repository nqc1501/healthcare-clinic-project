package com.example.diagnosisservice.controller;

import com.example.diagnosisservice.model.Diagnostic;
import com.example.diagnosisservice.service.DiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/diagnostic")
public class DiagnosticController {

    @Autowired
    DiagnosticService sDia;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllDiagnostic() {
        return ResponseEntity.ok(sDia.findAllDiagnostic());
    }

    @PostMapping("/add-new-diagnostic")
    public ResponseEntity<?> addNewDiagnostic(@RequestBody Diagnostic request) {
        return ResponseEntity.ok(sDia.addNewDiagnostic(request));
    }
}
