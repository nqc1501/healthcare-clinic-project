package com.example.authservice.controller;

import com.example.authservice.model.Credential;
import com.example.authservice.payload.req.RegisterRequest;
import com.example.authservice.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class CredentialController {

    @Autowired
    CredentialService sCredential;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(sCredential.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> addPatientAccount(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(sCredential.addPatientAccount(request));
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<?> changePassword(@PathVariable Integer id, @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(sCredential.changePassword(id, request));
    }

    @PutMapping
    public ResponseEntity<?> updateAccount(@RequestBody Credential credential) {
        return ResponseEntity.ok(sCredential.updateAccount(credential));
    }
}
