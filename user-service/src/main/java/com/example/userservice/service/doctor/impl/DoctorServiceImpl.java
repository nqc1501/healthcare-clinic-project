package com.example.userservice.service.doctor.impl;

import com.example.userservice.model.Doctor;
import com.example.userservice.model.UserShift;
import com.example.userservice.payload.AppResponse;
import com.example.userservice.repository.DoctorRepository;
import com.example.userservice.repository.UserShiftRepository;
import com.example.userservice.service.doctor.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository rDoctor;

    @Autowired
    UserShiftRepository rUserShift;

    @Override
    public AppResponse addDoctor(Doctor doctor) {
        if (doctor.getCredentialId() == null) {
            return new AppResponse("Thông tin đăng nhập không được để trống", false);
        }

        Optional<Doctor> dbDoctor = rDoctor.findByCredentialId(doctor.getCredentialId());
        if (dbDoctor.isPresent()) {
            return new AppResponse("Thông tin đăng nhập đã được sử dụng", false);
        }

        doctor.setId(generateId());
        rDoctor.save(doctor);
        return new AppResponse("Lưu thông tin thành công", true);
    }

    @Override
    public List<Doctor> findAllDoctors() {
        return rDoctor.findAll();
    }

    @Override
    public Doctor findById(String id) {
        Optional<Doctor> optional = rDoctor.findById(id);
        return optional.orElseGet(() -> null);
    }

    @Override
    public AppResponse registerShift(List<UserShift> request) {
        try {
            if (ObjectUtils.isEmpty(request)) {
                return new AppResponse("Vui lòng không để trống", false);
            }

            for (UserShift u: request) {
                rUserShift.save(u);
            }

            return new AppResponse("Lưu thành công", true);

        } catch (Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    private String generateId() {
        String randomUUID = UUID.randomUUID().toString();
        return "DR" + randomUUID.substring(0, 8);
    }
}
