package com.example.facilityscheduling.service.impl;

import com.example.facilityscheduling.model.Schedule;
import com.example.facilityscheduling.payload.req.ScheduleRequest;
import com.example.facilityscheduling.payload.res.DoctorResponse;
import com.example.facilityscheduling.repository.ScheduleRepository;
import com.example.facilityscheduling.service.ScheduleService;
import com.example.responsehandling.payload.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository rSchedule;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public List<Schedule> getBySpecialtyId(Integer id) {
        List<DoctorResponse> listDoctor = webClientBuilder.build().get()
                .uri("http://user-service/api/v1/specialty/{id}/list-doctor", id)
                .retrieve()
                .bodyToFlux(DoctorResponse.class)
                .collectList()
                .block();

        if (listDoctor != null) {
            List<String> listDoctorId = listDoctor.stream().map(DoctorResponse::getId).toList();
            return rSchedule.findByDoctorIdIn(listDoctorId);
        }

        return null;
    }

    @Override
    public List<Schedule> getAllByDoctorId(String doctorId) {
        return rSchedule.findByDoctorId(doctorId);
    }

    @Override
    public AppResponse addRoom(Integer id, ScheduleRequest request) {
        try {

            if (ObjectUtils.isEmpty(request)) {
                return new AppResponse("Không được để trống", false);
            }

            Schedule existingSchedule = rSchedule.findById(id).orElse(null);
            if (existingSchedule == null) {
                return new AppResponse("Không tồn tại", false);
            }

            existingSchedule.setRoomId(request.getRoomId());

            rSchedule.save(existingSchedule);

            return new AppResponse("Thêm phòng thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
