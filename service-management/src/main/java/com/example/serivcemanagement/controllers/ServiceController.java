package com.example.serivcemanagement.controllers;

import com.example.serivcemanagement.dto.req.SpecialistRequest;
import com.example.serivcemanagement.models.Service;
import com.example.serivcemanagement.models.Specialist;
import com.example.serivcemanagement.services.ServiceFunction;
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
    @GetMapping("find-all-services")
    public ResponseEntity<?> findAllServices() {
        return ResponseEntity.ok(sService.findAllServices());
    }

    @GetMapping("/find-all-by-specialist/{specialist}")
    public ResponseEntity<?> findAllBySpecialist(@PathVariable String specialist) {
        return ResponseEntity.ok(sService.findAllBySpecialist(specialist));
    }

    @PostMapping("/add-new-services")
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
