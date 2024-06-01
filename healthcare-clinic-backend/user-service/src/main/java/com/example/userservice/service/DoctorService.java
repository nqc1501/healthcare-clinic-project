package com.example.userservice.service;

import com.example.responsehandling.payload.response.AppResponse;
import com.example.userservice.model.Doctor;

import java.util.List;

public interface DoctorService {

    List<Doctor> getAllDoctor();

    Doctor getById(String id);

    AppResponse addDoctor(Doctor doctor);

    AppResponse uploadImage(String image);

    AppResponse updateDoctor(Doctor doctor);

    AppResponse deleteDoctor(String id);
}
