package com.example.healthcareservice.service.impl;

import com.example.healthcareservice.model.Prescription;
import com.example.healthcareservice.repository.PrescriptionRepository;
import com.example.healthcareservice.service.PrescriptionService;
import com.example.responsehandling.payload.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    PrescriptionRepository rPrescription;

    @Override
    public List<Prescription> getByDiagnosisId(Integer diagnosisId) {
        return rPrescription.findByDiagnosisId(diagnosisId);
    }

    @Override
    public AppResponse addPrescription(Prescription prescription) {
        try {
            if (ObjectUtils.isEmpty(prescription.getDiagnosis())) {
                return new AppResponse("Chẩn đoán không được để trống", false);
            }

            if (ObjectUtils.isEmpty(prescription.getListMedication())) {
                return new AppResponse("Đơn thuốc không được để trống", false);
            }

            rPrescription.save(prescription);

            return new AppResponse("Thêm mới đơn thuốc thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
