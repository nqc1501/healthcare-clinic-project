package com.example.billingservice.controller;

import com.example.billingservice.model.Bill;
import com.example.billingservice.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BillController {

    @Autowired
    BillService sBill;

    @GetMapping("/patient/{patientId}/bill")
    public ResponseEntity<?> getAllBillByPatientId(@PathVariable String patientId) {
        return ResponseEntity.ok(sBill.getBillByPatient(patientId));
    }

    @PostMapping
    public ResponseEntity<?> addBill(@RequestBody Bill bill) {
        return ResponseEntity.ok(sBill.addBill(bill));
    }
}
