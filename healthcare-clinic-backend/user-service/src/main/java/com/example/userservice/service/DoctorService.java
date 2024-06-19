package com.example.userservice.service;

import com.example.responsehandling.payload.response.AppResponse;
import com.example.userservice.model.Doctor;
import com.example.userservice.payload.ScheduleRequest;

import java.util.List;

public interface DoctorService {

    List<Doctor> getAllDoctor();

    Doctor getById(String id);

    List<Doctor> getAllBySpecialtyId(Integer specialtyId);

    AppResponse addDoctor(Doctor doctor);

    AppResponse registerShift(String doctorId, ScheduleRequest scheduleRequest);

    AppResponse uploadImage(String id, String image);

    AppResponse updateDoctor(Doctor doctor);

    AppResponse deleteDoctor(String id);
}
