package com.example.scheduleservice.service;

import com.example.scheduleservice.model.Schedule;
import com.example.scheduleservice.model.Shift;
import com.example.scheduleservice.payload.AppResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {

    AppResponse addRoomToSchedule(List<Schedule> request);

    Boolean checkSchedule(Shift shift);
}
