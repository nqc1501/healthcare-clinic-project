package com.example.supplyservice.controllers;

import com.example.supplyservice.services.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {

    @Autowired
    SupplyService sSupply;

    @GetMapping("/supply/find-all-supplies")
    public ResponseEntity<?> findAllSupplies() {
        return ResponseEntity.ok(sSupply.findAllSupplies());
    }
}
