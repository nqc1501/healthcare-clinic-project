package com.example.scheduleservice.repository;

import com.example.scheduleservice.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {

    @Query("select s from Shift s " +
            "where s.dayOfWeek = :dayOfWeek " +
            "and s.startTime = :startTime " +
            "and s.endTime = :endTime " +
            "and (s.startDate <= :endDate and s.endDate >= :startDate)")
    List<Shift> findConflictingShift(@Param("dayOfWeek") String dayOfWeek,
                                     @Param("startTime") String startTime,
                                     @Param("endTime") String endTime,
                                     @Param("startDate") Date startDate,
                                     @Param("endDate") Date endDate);
}
