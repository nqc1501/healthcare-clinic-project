package com.example.userservice.repository;

import com.example.userservice.model.UserShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserShiftRepository extends JpaRepository<UserShift, Integer> {
}
