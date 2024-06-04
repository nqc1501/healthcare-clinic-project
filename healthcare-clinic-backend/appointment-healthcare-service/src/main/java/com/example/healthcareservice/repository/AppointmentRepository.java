package com.example.healthcareservice.repository;

import com.example.healthcareservice.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("select a from Appointment a where a.patientId = :patientId and a.time = :time")
    Optional<Appointment> findByPatient(@Param("patientId") String patientId,
                                        @Param("time") Date time);
}
