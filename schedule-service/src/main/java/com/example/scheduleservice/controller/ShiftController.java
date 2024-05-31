package com.example.scheduleservice.controller;

import com.example.scheduleservice.model.Shift;
import com.example.scheduleservice.model.UserShift;
import com.example.scheduleservice.service.ShiftService;
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

    @GetMapping("/find-all-shift")
    public ResponseEntity<?> findAllShift() {
        return ResponseEntity.ok(sShift.findAllShift());
    }

    @GetMapping("/find-by-user-id/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(sShift.findByUserId(userId));
    }

    @PostMapping("/add-new-shift")
    public ResponseEntity<?> addNewShift(@RequestBody Shift request) {
        return ResponseEntity.ok(sShift.addNewShift(request));
    }

    @PostMapping("/register-shift")
    public ResponseEntity<?> registerShiftForUser(@RequestBody List<UserShift> request) {
        return ResponseEntity.ok(sShift.registerShift(request));
    }

    @PutMapping("/update-shift")
    public ResponseEntity<?> updateShift(@RequestBody Shift request) {
        return ResponseEntity.ok(sShift.updateShift(request));
    }

    @DeleteMapping("/delete-shift/{id}")
    public ResponseEntity<?> deleteShift(@PathVariable Integer id) {
        return ResponseEntity.ok(sShift.deleteShift(id));
    }
}
