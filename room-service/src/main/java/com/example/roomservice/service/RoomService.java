package com.example.roomservice.service;

import com.example.roomservice.model.Room;
import com.example.roomservice.payload.AppResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {

    List<Room> findAllRooms();

    AppResponse addNewRoom(Room room);
}
