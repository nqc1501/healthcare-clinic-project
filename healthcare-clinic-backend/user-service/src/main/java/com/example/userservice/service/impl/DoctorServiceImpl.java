package com.example.userservice.service.impl;

import com.example.responsehandling.payload.response.AppResponse;
import com.example.userservice.model.Doctor;
import com.example.userservice.model.Schedule;
import com.example.userservice.dto.ScheduleRequest;
import com.example.userservice.repository.DoctorRepository;
import com.example.userservice.repository.ScheduleRepository;
import com.example.userservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository rDoctor;

    @Autowired
    ScheduleRepository rSchedule;

    @Override
    public List<Doctor> getAllDoctor() {
        return rDoctor.findAll();
    }

    @Override
    public Doctor getById(String id) {
        return rDoctor.findById(id).orElse(null);
    }

    @Override
    public List<Doctor> getAllBySpecialtyId(Integer specialtyId) {
        return rDoctor.findBySpecialtyId(specialtyId);
    }

    @Override
    public AppResponse addDoctor(Doctor doctor) {
        try {
            rDoctor.save(doctor);
            return new AppResponse("Tạo mới bác sĩ thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse registerShift(String doctorId, ScheduleRequest scheduleRequest) {
        try {
            if (doctorId == null) {
                return new AppResponse("Không được để trống mã bác sĩ", false);
            }

            if (ObjectUtils.isEmpty(scheduleRequest)) {
                return new AppResponse("Không được để trống", false);
            }

            Schedule schedule = new Schedule();
            schedule.setDoctorId(doctorId);
            schedule.setShiftId(scheduleRequest.getShiftId());

            rSchedule.save(schedule);

            return new AppResponse("Lưu đăng ký thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse uploadImage(String id, String image) {
        return null;
    }

    @Override
    public AppResponse updateDoctor(Doctor doctor) {
        try {
            Doctor existingDoctor = rDoctor.findById(doctor.getId()).orElse(null);
            if (existingDoctor == null) {
                return new AppResponse("Bác sĩ này không tồn tại", false);
            }

            rDoctor.save(doctor);

            return new AppResponse("Chỉnh sửa thông tin bác sĩ thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse deleteDoctor(String id) {
        try {
            Doctor existingDoctor = rDoctor.findById(id).orElse(null);
            if (existingDoctor == null) {
                return new AppResponse("Bác sĩ này không tồn tại", false);
            }

            rDoctor.delete(existingDoctor);

            return new AppResponse("Xóa bác sĩ thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
