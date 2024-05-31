package com.example.scheduleservice.repository;

import com.example.scheduleservice.model.UserShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserShiftRepository extends JpaRepository<UserShift, Integer> {

    List<UserShift> findAllByUserId(String userId);

    @Query("select u from UserShift u where u.userId = :userId and u.shiftId = :shiftId")
    Optional<UserShift> findByUserIdAndShiftId(@Param("userId") String userId, @Param("shiftId") Integer shiftId);
}
