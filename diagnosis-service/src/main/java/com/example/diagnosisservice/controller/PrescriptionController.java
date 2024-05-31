package com.example.diagnosisservice.controller;

import com.example.diagnosisservice.model.Prescription;
import com.example.diagnosisservice.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/prescription")
public class PrescriptionController {

    @Autowired
    PrescriptionService sPre;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllPrescription() {
        return ResponseEntity.ok(sPre.findAllPrescription());
    }

    @PostMapping("/add-new-prescription")
    public ResponseEntity<?> addNewPrescription(@RequestBody Prescription request) {
        return ResponseEntity.ok(sPre.addNewPrescription(request));
    }

}
