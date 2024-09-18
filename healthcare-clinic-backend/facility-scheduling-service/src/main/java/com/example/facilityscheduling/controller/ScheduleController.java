package com.example.facilityscheduling.controller;

import com.example.facilityscheduling.dto.req.ScheduleRequest;
import com.example.facilityscheduling.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService sSchedule;

    @GetMapping("/specialty/{specialtyId}/schedule-registry")
    public ResponseEntity<?> getAllScheduleBySpecialtyId(@Valid @PathVariable Integer specialtyId) {
        return ResponseEntity.ok(sSchedule.getBySpecialtyId(specialtyId));
    }

    @GetMapping("/doctor")
    public ResponseEntity<?> getAllByDoctor(@RequestParam String doctorId) {
        return ResponseEntity.ok(sSchedule.getAllByDoctorId(doctorId));
    }

    @PostMapping("/{id}/add-room")
    public ResponseEntity<?> addRoomToSchedule(@PathVariable Integer id, @RequestBody ScheduleRequest request) {
        return ResponseEntity.ok(sSchedule.addRoom(id, request));
    }
}
