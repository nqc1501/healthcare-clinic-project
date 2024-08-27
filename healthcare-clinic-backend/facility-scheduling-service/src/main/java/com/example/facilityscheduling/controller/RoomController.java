package com.example.facilityscheduling.controller;

import com.example.facilityscheduling.model.Room;
import com.example.facilityscheduling.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    RoomService sRoom;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRoom() {
        return ResponseEntity.ok(sRoom.getAllRoom());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(sRoom.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> addRoom(@ModelAttribute Room room) {
        return ResponseEntity.ok(sRoom.addRoom(room));
    }

    @PutMapping
    public ResponseEntity<?> updateRoom(@ModelAttribute Room room) {
        return ResponseEntity.ok(sRoom.updateRoom(room));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Integer id) {
        return ResponseEntity.ok(sRoom.deleteRoom(id));
    }
}
