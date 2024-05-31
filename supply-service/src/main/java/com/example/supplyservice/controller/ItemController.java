package com.example.supplyservice.controller;

import com.example.supplyservice.model.Medication;
import com.example.supplyservice.model.Supply;
import com.example.supplyservice.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/item")
public class ItemController {

    @Autowired
    SupplyService sSup;

    // supply
    @GetMapping("/supply/find-all-supplies")
    public ResponseEntity<?> findAllSupplies() {
        return ResponseEntity.ok(sSup.findAllSupplies());
    }

    @PostMapping("/supply/add-new-supply")
    public ResponseEntity<?> addNewSupply(@RequestBody Supply request) {
        return ResponseEntity.ok(sSup.addNewSupply(request));
    }

    @PutMapping("/supply/update-supply")
    public ResponseEntity<?> updateSupply(@RequestBody Supply request) {
        return ResponseEntity.ok(sSup.updateSupply(request));
    }

    // medication
    @GetMapping("/medication/find-all-medication")
    public ResponseEntity<?> findALlMedication() {
        return ResponseEntity.ok(sSup.findAllMedication());
    }

    @PostMapping("/medication/add-new-medication")
    public ResponseEntity<?> addNewMedication(@RequestBody Medication request) {
        return ResponseEntity.ok(sSup.addNewMedication(request));
    }

    @PutMapping("/medication/update-medication")
    public ResponseEntity<?> updateMedication(@RequestBody Medication request) {
        return  ResponseEntity.ok(sSup.updateMedication(request));
    }
}
