package com.example.facilityscheduling.service;

import com.example.facilityscheduling.model.Schedule;
import com.example.facilityscheduling.payload.ScheduleRequest;
import com.example.responsehandling.payload.response.AppResponse;

public interface ScheduleService {

    AppResponse addRoom(Schedule schedule);
}
