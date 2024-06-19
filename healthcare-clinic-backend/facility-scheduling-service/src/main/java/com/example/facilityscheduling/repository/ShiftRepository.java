package com.example.facilityscheduling.repository;

import com.example.facilityscheduling.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {

    @Query(value = "select s from Shift s where s.dayOfWeek = :dayOfWeek and :time >= s.startTime " +
            "and :time <= s.endTime and :date between s.startDate and s.endDate")
    List<Shift> findByAppointment(@Param("date") Date date,
                                  @Param("dayOfWeek") String dayOfWeek,
                                  @Param("time") String time);
}
