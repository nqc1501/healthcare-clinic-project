package com.example.facilityscheduling.repository;

import com.example.facilityscheduling.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    Optional<Room> findByName(String name);

    List<Room> findByFloorId(Integer floorId);
}
