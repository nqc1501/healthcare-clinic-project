package com.example.userservice.controllers;

import com.example.userservice.models.Doctor;
import com.example.userservice.services.doctor.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping("/find-all-doctors")
    public ResponseEntity<?> findAllDoctors() {
        return ResponseEntity.ok(doctorService.findAllDoctors());
    }

    @GetMapping("find-doctor-by-id")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok(doctorService.findById(id));
    }

    @PostMapping("/add-new-doctor")
    public ResponseEntity<?> addNewDoctor(@ModelAttribute Doctor doctor) {
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }
}
