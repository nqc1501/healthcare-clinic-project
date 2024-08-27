package com.example.healthcareservice.controller;

import com.example.healthcareservice.model.item.Medication;
import com.example.healthcareservice.model.item.Supply;
import com.example.healthcareservice.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/medication")
public class MedicationController {

    @Autowired
    MedicationService sMedication;

    @GetMapping
    public ResponseEntity<?> getAllMedication() {
        return ResponseEntity.ok(sMedication.getAllMedication());
    }

    @PostMapping
    public ResponseEntity<?> addMedication(@RequestBody Medication medication) {
        return ResponseEntity.ok(sMedication.addMedication(medication));
    }

    @PutMapping
    public ResponseEntity<?> updateSupply(@RequestBody Medication medication) {
        return ResponseEntity.ok(sMedication.updateMedication(medication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupply(@PathVariable Integer id) {
        return ResponseEntity.ok(sMedication.deleteMedication(id));
    }
}
