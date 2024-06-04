package com.example.userservice.controller;

import com.example.userservice.model.Doctor;
import com.example.userservice.payload.ScheduleRequest;
import com.example.userservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class DoctorController {

    @Autowired
    DoctorService sDoctor;

    @GetMapping("/doctor/")
    public ResponseEntity<?> getAllDoctor() {
        return ResponseEntity.ok(sDoctor.getAllDoctor());
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntity.ok(sDoctor.getById(id));
    }

    @GetMapping("/specialty/{specialtyId}/list-doctor")
    public ResponseEntity<?> getAllBySpecialtyId(@PathVariable Integer specialtyId) {
        return ResponseEntity.ok(sDoctor.getAllBySpecialtyId(specialtyId));
    }

    @PostMapping("/doctor/upload-image")
    public ResponseEntity<?> uploadImage(@RequestBody String image) {
        return ResponseEntity.ok(sDoctor.uploadImage(image));
    }

    @PostMapping("/doctor/{id}/register-shift")
    public ResponseEntity<?> registerShift(@PathVariable String id, @RequestBody ScheduleRequest scheduleRequest) {
        return ResponseEntity.ok(sDoctor.registerShift(id, scheduleRequest));
    }

    @PutMapping("/doctor")
    public ResponseEntity<?> updateDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(sDoctor.updateDoctor(doctor));
    }

    @DeleteMapping("/doctor/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable String id) {
        return ResponseEntity.ok(sDoctor.deleteDoctor(id));
    }
}
