package com.example.facilityscheduling.repository;

import com.example.facilityscheduling.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findByDoctorIdIn(List<String> listDoctorId);

    List<Schedule> findByDoctorId(String doctorId);
}
