package com.example.facilityscheduling.controller;

import com.example.facilityscheduling.model.Schedule;
import com.example.facilityscheduling.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService sSchedule;

    @PostMapping("/add-room")
    public ResponseEntity<?> addRoomToSchedule(@RequestBody Schedule schedule) {
        return ResponseEntity.ok(sSchedule.addRoom(schedule));
    }
}
