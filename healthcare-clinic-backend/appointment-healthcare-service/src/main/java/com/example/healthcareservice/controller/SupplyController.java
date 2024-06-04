package com.example.healthcareservice.controller;

import com.example.healthcareservice.model.item.Supply;
import com.example.healthcareservice.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class SupplyController {

    @Autowired
    SupplyService sSupply;

    @GetMapping("/supply")
    public ResponseEntity<?> getAllSupply() {
        return ResponseEntity.ok(sSupply.getAllSupply());
    }

    @PostMapping("/supply")
    public ResponseEntity<?> addSupply(@RequestBody Supply supply) {
        return ResponseEntity.ok(sSupply.addSupply(supply));
    }

    @PostMapping("/room/{roomId}/add-supply")
    public ResponseEntity<?> addSupplyToRoom(@PathVariable Integer roomId) {
        return ResponseEntity.ok(sSupply.addSupplyToRoom(roomId));
    }
}
