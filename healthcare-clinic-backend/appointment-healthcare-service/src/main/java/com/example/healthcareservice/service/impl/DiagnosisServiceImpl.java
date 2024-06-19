package com.example.healthcareservice.service.impl;

import com.example.healthcareservice.model.Appointment;
import com.example.healthcareservice.model.Diagnosis;
import com.example.healthcareservice.payload.req.AppointmentRequest;
import com.example.healthcareservice.repository.AppointmentRepository;
import com.example.healthcareservice.repository.DiagnosisRepository;
import com.example.healthcareservice.service.DiagnosisService;
import com.example.responsehandling.payload.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    @Autowired
    DiagnosisRepository rDiagnosis;

    @Autowired
    AppointmentRepository rAppointment;

    @Override
    public AppResponse getDiagnosisByPatientId(String patientId, AppointmentRequest request) {
        try {
            Appointment appointment = rAppointment.findByPatient(patientId, request.getDate(), request.getHour()).orElse(null);

            if (appointment == null) {
                return new AppResponse("Chưa hẹn lịch khám", false);
            }

            List<Diagnosis> diagnosis = rDiagnosis.findByAppointmentId(appointment.getId());

            if (diagnosis == null) {
                return new AppResponse("Hiện chưa có chẩn đoán", false);
            }

            return new AppResponse(diagnosis);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse addDiagnosis(Diagnosis diagnosis) {
        try {
            if (ObjectUtils.isEmpty(diagnosis.getAppointment())) {
                return new AppResponse("Cuộc hẹn không được để trống", false);
            }

            if (ObjectUtils.isEmpty(diagnosis.getConclusion())) {
                return new AppResponse("Kết luận không được để trống", false);
            }

            rDiagnosis.save(diagnosis);

            return new AppResponse("Thêm chẩn đoán thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public List<Diagnosis> getAllByAppointmentId(Integer id) {
        return rDiagnosis.findByAppointmentId(id);
    }
}
