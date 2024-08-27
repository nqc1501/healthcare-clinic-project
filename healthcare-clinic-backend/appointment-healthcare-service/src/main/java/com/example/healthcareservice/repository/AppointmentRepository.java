package com.example.healthcareservice.repository;

import com.example.healthcareservice.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("select a from Appointment a where a.patientId = :patientId and a.date = :date and a.hour = :hour")
    Optional<Appointment> findByPatient(@Param("patientId") String patientId,
                                        @Param("date") Date date,
                                        @Param("hour") String hour);

    List<Appointment> findByPatientId(String id);

    List<Appointment> findByDoctorId(String doctorId);
}
