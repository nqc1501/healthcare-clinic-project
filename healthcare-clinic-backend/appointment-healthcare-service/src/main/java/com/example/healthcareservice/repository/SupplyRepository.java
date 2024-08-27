package com.example.healthcareservice.repository;

import com.example.healthcareservice.model.item.Supply;
import com.example.responsehandling.payload.response.AppResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Integer> {

    List<Supply> findByRoomId(Integer roomId);
}
