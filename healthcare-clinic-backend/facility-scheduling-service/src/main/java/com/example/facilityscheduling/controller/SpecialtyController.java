package com.example.facilityscheduling.controller;

import com.example.facilityscheduling.model.Specialty;
import com.example.facilityscheduling.service.RoomService;
import com.example.facilityscheduling.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
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
    public ResponseEntity<?> getAllSpecialty() {
        return ResponseEntity.ok(sSpecialty.getAllSpecialty());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        if (ObjectUtils.isEmpty(id)) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(sSpecialty.getById(id));
    }

    @GetMapping("/{id}/list-room")
    public ResponseEntity<?> getRoomsBySpecialtyId(@PathVariable Integer id) {
        return ResponseEntity.ok(sRoom.getRoomsBySpecialtyId(id));
    }
 }
