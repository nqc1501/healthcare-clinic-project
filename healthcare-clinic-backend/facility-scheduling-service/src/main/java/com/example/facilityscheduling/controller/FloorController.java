package com.example.facilityscheduling.controller;

import com.example.facilityscheduling.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/floor")
public class FloorController {

    @Autowired
    FloorService sFloor;

    @GetMapping
    public ResponseEntity<?> getAllFloor() {
        return ResponseEntity.ok(sFloor.getAllFloor());
    }
}
