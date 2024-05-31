package com.example.scheduleservice.service;

import com.example.scheduleservice.model.Shift;
import com.example.scheduleservice.model.UserShift;
import com.example.scheduleservice.payload.AppResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShiftService {

    List<Shift> findAllShift();

    List<Shift> findByUserId(String userId);

    AppResponse addNewShift(Shift request);

    AppResponse updateShift(Shift request);

    AppResponse deleteShift(Integer id);

    AppResponse registerShift(List<UserShift> request);
}
