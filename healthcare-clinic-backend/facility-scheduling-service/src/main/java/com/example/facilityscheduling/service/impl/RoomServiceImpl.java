package com.example.facilityscheduling.service.impl;

import com.example.facilityscheduling.model.Room;
import com.example.facilityscheduling.repository.RoomRepository;
import com.example.facilityscheduling.service.RoomService;
import com.example.responsehandling.payload.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository rRoom;

    @Override
    public List<Room> getAllRoom() {
        return rRoom.findAll();
    }

    @Override
    public Room getById(Integer id) {
        return rRoom.findById(id).orElse(null);
    }

    @Override
    public AppResponse addRoom(Room room) {
        try {
            if (ObjectUtils.isEmpty(room)) {
                return new AppResponse("Không được để trống", false);
            }

            Room existingRoom = rRoom.findByName(room.getName()).orElse(null);
            if (existingRoom != null) {
                return new AppResponse("Phòng đã tồn tại", false);
            }

            rRoom.save(room);

            return new AppResponse("Thêm phòng thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse updateRoom(Room room) {
        try {
            Room existingRoom = rRoom.findById(room.getId()).orElse(null);
            if (existingRoom == null) {
                return new AppResponse("Phòng không tồn tại", false);
            }

            rRoom.save(room);

            return new AppResponse("Sửa thông tin phòng thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse deleteRoom(Integer id) {
        try {
            Room existingRoom = rRoom.findById(id).orElse(null);
            if (existingRoom == null) {
                return new AppResponse("Phòng không tồn tại", false);
            }

            rRoom.delete(existingRoom);

            return new AppResponse("Xóa phòng thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
