package com.example.healthcareservice.repository;

import com.example.healthcareservice.model.item.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Integer> {
}
