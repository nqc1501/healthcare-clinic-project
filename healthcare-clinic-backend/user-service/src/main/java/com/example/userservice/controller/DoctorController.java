package com.example.userservice.controller;

import com.example.userservice.model.Doctor;
import com.example.userservice.dto.ScheduleRequest;
import com.example.userservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    @Autowired
    DoctorService sDoctor;

    @GetMapping
    public ResponseEntity<?> getAllDoctor() {
        return ResponseEntity.ok(sDoctor.getAllDoctor());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntity.ok(sDoctor.getById(id));
    }

    @GetMapping("/specialty")
    public ResponseEntity<?> getAllBySpecialtyId(@RequestParam Integer specialtyId) {
        return ResponseEntity.ok(sDoctor.getAllBySpecialtyId(specialtyId));
    }

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<?> uploadImage(@PathVariable String id, @RequestBody String image) {
        return ResponseEntity.ok(sDoctor.uploadImage(id, image));
    }

    @PostMapping("/{id}/register-shift")
    public ResponseEntity<?> registerShift(@PathVariable String id, @RequestBody ScheduleRequest scheduleRequest) {
        return ResponseEntity.ok(sDoctor.registerShift(id, scheduleRequest));
    }

    @PutMapping
    public ResponseEntity<?> updateDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(sDoctor.updateDoctor(doctor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable String id) {
        return ResponseEntity.ok(sDoctor.deleteDoctor(id));
    }
}
