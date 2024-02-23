package com.example.roomservice.services.impl;

import com.example.roomservice.models.Room;
import com.example.roomservice.payload.AppResponse;
import com.example.roomservice.repositories.RoomRepository;
import com.example.roomservice.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository rRoom;

    @Override
    public List<Room> findAllRooms() {
        return rRoom.findAll();
    }

    @Override
    public AppResponse addNewRoom(Room room) {

        if (rRoom.findByName(room.getName()).isPresent()) {
            return new AppResponse("Thông tin phòng này đã tồn tại", false);
        }

        return new AppResponse(rRoom.save(room));
    }
}
