package com.example.healthcareservice.controller;

import com.example.healthcareservice.model.item.Supply;
import com.example.healthcareservice.dto.req.SupplyRequest;
import com.example.healthcareservice.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/supply")
public class SupplyController {

    @Autowired
    SupplyService sSupply;

    @GetMapping
    public ResponseEntity<?> getAllSupply() {
        return ResponseEntity.ok(sSupply.getAllSupply());
    }

    @PostMapping
    public ResponseEntity<?> addSupply(@RequestBody Supply supply) {
        return ResponseEntity.ok(sSupply.addSupply(supply));
    }

    @PostMapping("/room")
    public ResponseEntity<?> addSupplyToRoom(@RequestParam Integer roomId, @RequestBody SupplyRequest request) {
        return ResponseEntity.ok(sSupply.addSupplyToRoom(roomId, request));
    }

    @PutMapping
    public ResponseEntity<?> updateSupply(@RequestBody Supply supply) {
        return ResponseEntity.ok(sSupply.updateSupply(supply));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupply(@PathVariable Integer id) {
        return ResponseEntity.ok(sSupply.deleteSupply(id));
    }
}
