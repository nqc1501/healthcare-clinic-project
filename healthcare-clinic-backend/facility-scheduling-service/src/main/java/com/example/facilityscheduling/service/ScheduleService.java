package com.example.facilityscheduling.service;

import com.example.facilityscheduling.model.Schedule;
import com.example.facilityscheduling.payload.req.ScheduleRequest;
import com.example.responsehandling.payload.response.AppResponse;

import java.util.List;

public interface ScheduleService {

    List<Schedule> getBySpecialtyId(Integer id);

    List<Schedule> getAllByDoctorId(String doctorId);

    AppResponse addRoom(Integer id, ScheduleRequest request);
}
