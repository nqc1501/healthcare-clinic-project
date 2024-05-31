package com.example.scheduleservice.controller;

import com.example.scheduleservice.model.Schedule;
import com.example.scheduleservice.model.Shift;
import com.example.scheduleservice.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService sSchedule;

    @GetMapping("/find-all-schedule")
    public ResponseEntity<?> findAllSchedule() {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/add-new-schedule")
    public ResponseEntity<?> addNewSchedule(@RequestBody List<Schedule> request) {
        return ResponseEntity.ok(sSchedule.addRoomToSchedule(request));
    }

    @GetMapping("/check-schedule")
    public ResponseEntity<?> isRoomForShift(@RequestBody Shift shift) {
        return ResponseEntity.ok(sSchedule.checkSchedule(shift));
    }
}
