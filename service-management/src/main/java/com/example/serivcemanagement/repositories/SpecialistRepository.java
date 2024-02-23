package com.example.serivcemanagement.repositories;

import com.example.serivcemanagement.models.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Integer> {

    Optional<Specialist> findByName(String name);

    @Query("SELECT spe FROM Specialist spe JOIN spe.listServices s WHERE s.id = :serviceId")
    Specialist findByServiceId(@Param("serviceId") Integer serviceId);
}
