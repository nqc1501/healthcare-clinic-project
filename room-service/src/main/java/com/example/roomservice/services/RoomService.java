package com.example.roomservice.services;

import com.example.roomservice.models.Room;
import com.example.roomservice.payload.AppResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {

    List<Room> findAllRooms();

    AppResponse addNewRoom(Room room);
}
