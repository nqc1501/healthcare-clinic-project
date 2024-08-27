package com.example.facilityscheduling.controller;

import com.example.facilityscheduling.model.Shift;
import com.example.facilityscheduling.payload.req.AppointmentRequest;
import com.example.facilityscheduling.payload.req.ScheduleRequest;
import com.example.facilityscheduling.service.ShiftService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/shift")
public class ShiftController {

    @Autowired
    ShiftService sShift;

    @GetMapping
    public ResponseEntity<List<Shift>> getAllShift() {
        return ResponseEntity.ok(sShift.getAllShift());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shift> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(sShift.getById(id));
    }

    @GetMapping("/find-by-appointment")
    public ResponseEntity<?> getByAppointment(@Valid @RequestBody AppointmentRequest request) {
        return ResponseEntity.ok(sShift.getByAppointment(request));
    }

    @PostMapping
    public ResponseEntity<?> addShift(@RequestBody Shift shift) {
        return ResponseEntity.ok(sShift.addShift(shift));
    }

    @PostMapping("/{id}/register-shift")
    public ResponseEntity<?> registerShift(@PathVariable Integer id, @RequestBody ScheduleRequest scheduleRequest) {
        return ResponseEntity.ok(sShift.registerShift(id, scheduleRequest));
    }

    @PutMapping
    public ResponseEntity<?> updateShift(@RequestBody Shift request) {
        return ResponseEntity.ok(sShift.updateShift(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShift(@PathVariable Integer id) {
        return ResponseEntity.ok(sShift.deleteShift(id));
    }
}
