package com.example.serivcemanagement.controllers;

import com.example.serivcemanagement.dto.req.SpecialistRequest;
import com.example.serivcemanagement.services.ServiceFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/service-management")
public class ServiceController {

    @Autowired
    ServiceFunction sService;

    @GetMapping("/find-all-services/{specialist}")
    public ResponseEntity<?> findAllBySpecialist(@PathVariable String specialist) {
        return ResponseEntity.ok(sService.findAllBySpecialist(specialist));
    }

    @PostMapping("/add-new-specialist")
    public ResponseEntity<?> addNewSpecialist(@RequestBody SpecialistRequest request) {
        return ResponseEntity.ok("");
    }

    @PutMapping("/add-new-services")
    public ResponseEntity<?> addNewServices(@RequestBody SpecialistRequest request) {
        return ResponseEntity.ok("");
    }

}
