package com.example.facilityscheduling.repository;

import com.example.facilityscheduling.model.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Integer> {

    Optional<Floor> findBySpecialtyId(Integer specialtyId);
}
