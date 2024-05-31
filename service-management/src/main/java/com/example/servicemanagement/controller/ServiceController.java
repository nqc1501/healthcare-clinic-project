package com.example.servicemanagement.controller;

import com.example.servicemanagement.dto.req.SpecialistRequest;
import com.example.servicemanagement.model.Service;
import com.example.servicemanagement.model.Specialist;
import com.example.servicemanagement.service.ServiceFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/service-management")
public class ServiceController {

    @Autowired
    ServiceFunction sService;


    // specialist
    @GetMapping("/find-all-specialist")
    public ResponseEntity<?> findAllSpecialist() {
        return ResponseEntity.ok(sService.findAllSpecialist());
    }

    @GetMapping("find-specialist-by-service-id/{id}")
    public ResponseEntity<?> findSpecialistByServiceI(@PathVariable Integer id) {
        return ResponseEntity.ok(sService.findSpecialistByServiceId(id));
    }

    @PostMapping("/add-new-specialist")
    public ResponseEntity<?> addNewSpecialist(@RequestBody Specialist request) {
        return ResponseEntity.ok(sService.addNewSpecialist(request));
    }

    @PutMapping("update-specialist")
    public ResponseEntity<?> updateSpecialist(@RequestBody Specialist request) {
        return ResponseEntity.ok(sService.updateSpecialist(request));
    }

    // service
    @GetMapping("find-all-service")
    public ResponseEntity<?> findAllServices() {
        return ResponseEntity.ok(sService.findAllServices());
    }

    @GetMapping("/find-all-by-specialist/{specialist}")
    public ResponseEntity<?> findAllBySpecialist(@PathVariable String specialist) {
        return ResponseEntity.ok(sService.findAllBySpecialist(specialist));
    }

    @PostMapping("/add-new-service")
    public ResponseEntity<?> addNewServices(@ModelAttribute SpecialistRequest request) {
        return ResponseEntity.ok("");
    }

    @PutMapping("/update-service")
    public ResponseEntity<?> updateService(@RequestBody Service request) {
        return ResponseEntity.ok(sService.updateService(request));
    }

    @DeleteMapping("/delete-service-by-id/{id}")
    public ResponseEntity<?> deleteServiceById(@PathVariable Integer id) {
        return ResponseEntity.ok(sService.deleteServiceById(id));
    }

}
