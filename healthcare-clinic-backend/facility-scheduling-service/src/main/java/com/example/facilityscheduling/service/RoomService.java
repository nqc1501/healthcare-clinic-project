package com.example.facilityscheduling.service;

import com.example.facilityscheduling.model.Room;
import com.example.responsehandling.payload.response.AppResponse;

import java.util.List;

public interface RoomService {

    List<Room> getAllRoom();

    Room getById(Integer id);

    AppResponse getRoomsBySpecialtyId(Integer specialtyId);

    AppResponse addRoom(Room room);

    AppResponse updateRoom(Room room);

    AppResponse deleteRoom(Integer id);
}
