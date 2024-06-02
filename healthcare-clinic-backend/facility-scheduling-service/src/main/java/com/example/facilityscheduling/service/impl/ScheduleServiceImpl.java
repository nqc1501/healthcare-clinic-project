package com.example.facilityscheduling.service.impl;

import com.example.facilityscheduling.model.Schedule;
import com.example.facilityscheduling.payload.ScheduleRequest;
import com.example.facilityscheduling.repository.ScheduleRepository;
import com.example.facilityscheduling.service.ScheduleService;
import com.example.responsehandling.payload.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository rSchedule;

    @Override
    public AppResponse addRoom(Schedule schedule) {
        try {

            if (ObjectUtils.isEmpty(schedule)) {
                return new AppResponse("Không được để trống", false);
            }

            Schedule existingSchedule = rSchedule.findById(schedule.getId()).orElse(null);
            if (existingSchedule == null) {
                return new AppResponse("Không tồn tại", false);
            }

            existingSchedule.setRoomId(schedule.getRoomId());

            rSchedule.save(existingSchedule);

            return new AppResponse("Thêm phòng thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
