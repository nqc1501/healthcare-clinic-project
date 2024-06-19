package com.example.healthcareservice.service;

import com.example.healthcareservice.model.Appointment;
import com.example.responsehandling.payload.response.AppResponse;

import java.util.List;

public interface AppointmentService {

    List<Appointment> getAllAppointment();

    List<Appointment> getByHealthCode(String healthCode);

    List<Appointment> getByPatientId(String patientId);

    List<Appointment> getByDoctorId(String doctorId);

    AppResponse addAppointment(String patientId, Appointment appointment);
}
