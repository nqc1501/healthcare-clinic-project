package com.example.roomservice.controller;

import com.example.roomservice.model.Room;
import com.example.roomservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    RoomService sRoom;

    @GetMapping("/find-all-rooms")
    public ResponseEntity<?> findAllRooms() {
        return ResponseEntity.ok(sRoom.findAllRooms());
    }

    @PostMapping("/add-new-room")
    public ResponseEntity<?> addNewRoom(@ModelAttribute Room room) {
        return ResponseEntity.ok(sRoom.addNewRoom(room));
    }
}
