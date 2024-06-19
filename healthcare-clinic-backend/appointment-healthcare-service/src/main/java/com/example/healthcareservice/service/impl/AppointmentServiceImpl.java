package com.example.healthcareservice.service.impl;

import com.example.healthcareservice.model.Appointment;
import com.example.healthcareservice.payload.res.PatientResponse;
import com.example.healthcareservice.repository.AppointmentRepository;
import com.example.healthcareservice.service.AppointmentService;
import com.example.responsehandling.payload.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository rAppointment;

    @Autowired
    WebClient.Builder webClientBuilder;

    @Override
    public List<Appointment> getAllAppointment() {
        return rAppointment.findAll();
    }

    @Override
    public List<Appointment> getByHealthCode(String healthCode) {

        PatientResponse patient = webClientBuilder.build().get()
                .uri("http://user-service/api/v1/patient/find-by-health-code/{healthCode}", healthCode)
                .retrieve()
                .bodyToFlux(PatientResponse.class)
                .blockFirst();

        if (patient != null) {
            return rAppointment.findByPatientId(patient.getId());
        }

        return null;
    }

    @Override
    public List<Appointment> getByPatientId(String patientId) {
        return rAppointment.findByPatientId(patientId);
    }

    @Override
    public List<Appointment> getByDoctorId(String doctorId) {
        return rAppointment.findByDoctorId(doctorId);
    }

    @Override
    public AppResponse addAppointment(String patientId, Appointment appointment) {
        return new AppResponse(rAppointment.save(appointment));
    }
}
