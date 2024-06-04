package com.example.healthcareservice.service.impl;

import com.example.healthcareservice.model.item.Supply;
import com.example.healthcareservice.repository.SupplyRepository;
import com.example.healthcareservice.service.SupplyService;
import com.example.responsehandling.payload.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    SupplyRepository rSupply;

    @Override
    public List<Supply> getAllSupply() {
        return rSupply.findAll();
    }

    @Override
    public AppResponse addSupply(Supply supply) {
        try {
            rSupply.save(supply);

            return new AppResponse("Thêm mới thiết bị thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public List<Supply> addSupplyToRoom(Integer roomId) {
        return rSupply.findByRoomId(roomId);
    }
}
