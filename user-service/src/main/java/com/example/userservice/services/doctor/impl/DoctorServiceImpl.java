package com.example.userservice.services.doctor.impl;

import com.example.userservice.models.Doctor;
import com.example.userservice.payload.AppResponse;
import com.example.userservice.repositories.DoctorRepository;
import com.example.userservice.services.doctor.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public AppResponse addDoctor(Doctor doctor) {
        if (doctor.getCredentialId() == null) {
            return new AppResponse("Thông tin đăng nhập không được để trống", false);
        }

        Optional<Doctor> dbDoctor = doctorRepository.findByCredentialId(doctor.getCredentialId());
        if (dbDoctor.isPresent()) {
            return new AppResponse("Thông tin đăng nhập đã được sử dụng", false);
        }

        doctor.setId(generateId());
        doctorRepository.save(doctor);
        return new AppResponse("Lưu thông tin thành công", true);
    }

    @Override
    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findById(String id) {
        Optional<Doctor> optional = doctorRepository.findById(id);
        return optional.orElseGet(() -> null);
    }

    private String generateId() {
        String randomUUID = UUID.randomUUID().toString();
        return "DR" + randomUUID.substring(0, 8);
    }
}
