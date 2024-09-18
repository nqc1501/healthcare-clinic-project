package com.example.facilityscheduling.service.impl;

import com.example.facilityscheduling.model.Schedule;
import com.example.facilityscheduling.model.Shift;
import com.example.facilityscheduling.dto.req.AppointmentRequest;
import com.example.facilityscheduling.dto.req.ScheduleRequest;
import com.example.facilityscheduling.repository.ScheduleRepository;
import com.example.facilityscheduling.repository.ShiftRepository;
import com.example.facilityscheduling.service.ShiftService;
import com.example.responsehandling.payload.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    ShiftRepository rShift;

    @Autowired
    ScheduleRepository rSchedule;

    @Override
    public List<Shift> getAllShift() {
        return rShift.findAll();
    }

    @Override
    public Shift getById(Integer id) {
        return rShift.findById(id).orElse(null);
    }

    @Override
    public List<Shift> getByAppointment(AppointmentRequest request) {
        return rShift.findByAppointment(request.getDate(), request.getDayOfWeek(), request.getHour());
    }

    @Override
    public AppResponse addShift(Shift shift) {
        try {
            if (ObjectUtils.isEmpty(shift)) {
                return new AppResponse("Ca làm việc không được để trống", false);
            }

            rShift.save(shift);

            return new AppResponse("Thêm mới ca làm việc thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse registerShift(Integer shiftId, ScheduleRequest scheduleRequest) {
        try {
            if (shiftId == null) {
                return new AppResponse("Không được để trống mã Ca làm", false);
            }

            if (ObjectUtils.isEmpty(scheduleRequest)) {
                return new AppResponse("Không được để trống", false);
            }

            Shift existingShift = rShift.findById(shiftId).orElse(null);
            if (existingShift == null) {
                return new AppResponse("Ca làm việc không tồn tại", false);
            }

            existingShift.setQuantityRegistered(existingShift.getQuantityRegistered() + 1);
            if (existingShift.getQuantityRegistered() >= existingShift.getQuantity()) {
                existingShift.setIsSelected(false);
            }

            Schedule schedule = new Schedule();
            schedule.setShiftId(shiftId);
            schedule.setDoctorId(scheduleRequest.getDoctorId());

            rShift.save(existingShift);
            rSchedule.save(schedule);

            return new AppResponse("Đăng ký ca làm việc thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse updateShift(Shift request) {
        try {
            Shift existingShift = rShift.findById(request.getId()).orElse(null);

            if (existingShift == null) {
                return new AppResponse("Ca làm việc không tồn tại", false);
            }

            rShift.save(request);

            return new AppResponse("Chỉnh sửa ca làm việc thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse deleteShift(Integer id) {
        try {
            Shift existingShift = rShift.findById(id).orElse(null);

            if (existingShift == null) {
                return new AppResponse("Ca làm việc không tồn tại", false);
            }

            rShift.delete(existingShift);

            return new AppResponse("Xóa ca làm việc thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
