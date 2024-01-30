package com.example.userservice.services.doctor;

import com.example.userservice.models.Doctor;
import com.example.userservice.payload.AppResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {
    AppResponse addDoctor(Doctor doctor);

    List<Doctor> findAllDoctors();

    Doctor findById(String id);
}
