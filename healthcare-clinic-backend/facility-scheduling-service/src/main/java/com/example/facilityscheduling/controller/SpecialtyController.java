package com.example.facilityscheduling.controller;

import com.example.facilityscheduling.model.Specialty;
import com.example.facilityscheduling.service.RoomService;
import com.example.facilityscheduling.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/specialty")
public class SpecialtyController {

    @Autowired
    SpecialtyService sSpecialty;

    @Autowired
    RoomService sRoom;

    @GetMapping
    public ResponseEntity<List<Specialty>> getAllSpecialty() {
        return ResponseEntity.ok(sSpecialty.getAllSpecialty());
    }

    @GetMapping("/{id}/list-room")
    public ResponseEntity<?> getRoomsBySpecialtyId(@PathVariable Integer id) {
        return ResponseEntity.ok(sRoom.getRoomsBySpecialtyId(id));
    }
 }
