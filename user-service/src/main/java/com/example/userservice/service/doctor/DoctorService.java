package com.example.userservice.service.doctor;

import com.example.userservice.model.Doctor;
import com.example.userservice.model.UserShift;
import com.example.userservice.payload.AppResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {
    AppResponse addDoctor(Doctor doctor);

    List<Doctor> findAllDoctors();

    Doctor findById(String id);

    AppResponse registerShift(List<UserShift> request);
}
