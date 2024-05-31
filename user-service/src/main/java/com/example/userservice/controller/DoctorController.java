package com.example.userservice.controller;

import com.example.userservice.model.Doctor;
import com.example.userservice.model.UserShift;
import com.example.userservice.service.doctor.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    @Autowired
    DoctorService sDoctor;

    @GetMapping("/find-all-doctors")
    public ResponseEntity<?> findAllDoctors() {
        return ResponseEntity.ok(sDoctor.findAllDoctors());
    }

    @GetMapping("find-doctor-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return ResponseEntity.ok(sDoctor.findById(id));
    }

    @PostMapping("/add-new-doctor")
    public ResponseEntity<?> addNewDoctor(@ModelAttribute Doctor doctor) {
        return ResponseEntity.ok(sDoctor.addDoctor(doctor));
    }

    @PostMapping("/register-shift")
    public ResponseEntity<?> registerShift(@RequestBody List<UserShift> request) {
        return ResponseEntity.ok(sDoctor.registerShift(request));
    }
}
