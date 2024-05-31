package com.example.scheduleservice.service.impl;

import com.example.scheduleservice.model.Schedule;
import com.example.scheduleservice.model.Shift;
import com.example.scheduleservice.payload.AppResponse;
import com.example.scheduleservice.repository.ScheduleRepository;
import com.example.scheduleservice.repository.ShiftRepository;
import com.example.scheduleservice.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository rSchedule;

    @Autowired
    ShiftRepository rShift;

    @Override
    public AppResponse addRoomToSchedule(List<Schedule> request) {
        try {
            if (request.isEmpty()) {
                return new AppResponse("Không được để trống", false);
            }

            for (Schedule s : request) {
                rSchedule.save(s);
            }

            return new AppResponse("Lưu thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã có lỗi xảy ra", false);
        }
    }

    @Override
    public Boolean checkSchedule(Shift shift) {
        List<Shift> listConflict = rShift.findConflictingShift(
                shift.getDayOfWeek(),
                shift.getStartTime(),
                shift.getEndTime(),
                shift.getStartDate(),
                shift.getEndDate()
        );
        List<Schedule> listSchedule = rSchedule.findAll();
        List<Shift> listRegistry = new ArrayList<>();

        for (Schedule s : listSchedule) {
            Optional<Shift> optShift = rShift.findById(s.getShiftId());
            optShift.ifPresent(listRegistry::add);
        }

        for (Shift s : listConflict) {
            if (listRegistry.contains(s)) {
                return false;
            }
        }

        return true;
    }
}
