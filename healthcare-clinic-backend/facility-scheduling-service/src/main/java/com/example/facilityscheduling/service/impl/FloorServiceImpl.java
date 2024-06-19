package com.example.facilityscheduling.service.impl;

import com.example.facilityscheduling.model.Floor;
import com.example.facilityscheduling.repository.FloorRepository;
import com.example.facilityscheduling.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorServiceImpl implements FloorService {

    @Autowired
    FloorRepository rFloor;

    @Override
    public List<Floor> getAllFloor() {
        return rFloor.findAll();
    }
}
