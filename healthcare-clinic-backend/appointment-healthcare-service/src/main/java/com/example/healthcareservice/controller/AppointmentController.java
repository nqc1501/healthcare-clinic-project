package com.example.healthcareservice.controller;

import com.example.healthcareservice.model.Appointment;
import com.example.healthcareservice.service.AppointmentService;
import com.example.healthcareservice.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService sAppointment;

    @Autowired
    DiagnosisService sDiagnosis;

    @GetMapping
    public ResponseEntity<?> getAllAppointment() {
        return ResponseEntity.ok(sAppointment.getAllAppointment());
    }

    @GetMapping("/by-health-code")
    public ResponseEntity<?> getByHealthCode(@RequestParam String healthCode) {
        return ResponseEntity.ok(sAppointment.getByHealthCode(healthCode));
    }

    @GetMapping("by-patient")
    public ResponseEntity<?> getByPatientId(@RequestParam String patientId) {
        return ResponseEntity.ok(sAppointment.getByPatientId(patientId));
    }

    @GetMapping("/by-doctor")
    public ResponseEntity<?> getByDoctorId(@RequestParam String doctorId) {
        return ResponseEntity.ok(sAppointment.getByDoctorId(doctorId));
    }

    @GetMapping("/{id}/diagnosis")
    public ResponseEntity<?> getAllDiagnosisById(@PathVariable Integer id) {
        return ResponseEntity.ok(sDiagnosis.getAllByAppointmentId(id));
    }

    @PostMapping("by-patient")
    public ResponseEntity<?> addAppointment(@RequestParam String patientId, @RequestBody Appointment appointment) {
        return ResponseEntity.ok(sAppointment.addAppointment(patientId, appointment));
    }
}
