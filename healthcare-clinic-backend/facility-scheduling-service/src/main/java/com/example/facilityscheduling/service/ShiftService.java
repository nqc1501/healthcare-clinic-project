package com.example.facilityscheduling.service;

import com.example.facilityscheduling.model.Shift;
import com.example.facilityscheduling.payload.req.AppointmentRequest;
import com.example.facilityscheduling.payload.req.ScheduleRequest;
import com.example.responsehandling.payload.response.AppResponse;

import java.util.Date;
import java.util.List;

public interface ShiftService {

    List<Shift> getAllShift();

    Shift getById(Integer id);

    List<Shift> getByAppointment(AppointmentRequest request);

    AppResponse addShift(Shift shift);

    AppResponse registerShift(Integer shiftId, ScheduleRequest scheduleRequest);

    AppResponse updateShift(Shift request);

    AppResponse deleteShift(Integer id);
}
