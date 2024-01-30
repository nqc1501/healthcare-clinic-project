package com.example.authservice.controllers;

import com.example.authservice.dto.req.LoginRequest;
import com.example.authservice.dto.req.RegisterRequest;
import com.example.authservice.services.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/account")
public class CredentialController {

    @Autowired
    private CredentialService sCredential;

    @GetMapping("/find-all-accounts")
    public ResponseEntity<?> findAllAccounts() {
        return ResponseEntity.ok(sCredential.findAllAccounts());
    }

    @GetMapping("/find-account-by-id/{id}")
    public ResponseEntity<?> findAccountById(@PathVariable Integer id) {
        return ResponseEntity.ok(sCredential.findAccountById(id));
    }

    @GetMapping("/find-account-by-email/{email}")
    public ResponseEntity<?> findAccountByEmail(@PathVariable String email) {
        return ResponseEntity.ok(sCredential.findAccountByEmail(email));
    }

    @PostMapping("/add-doctor-account")
    public ResponseEntity<?> addDoctorAccount(@ModelAttribute RegisterRequest request) {
        return ResponseEntity.ok(sCredential.addDoctorAccount(request));
    }

    @PostMapping("/add-patient-account")
    public ResponseEntity<?> addPatientAccount(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(sCredential.addPatientAccount(request));
    }

    @PutMapping("/update-account")
    public ResponseEntity<?> updateAccount(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(sCredential.updateAccount(request));
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer id) {
        sCredential.deleteAccount(id);
        return ResponseEntity.ok("");
    }
}
