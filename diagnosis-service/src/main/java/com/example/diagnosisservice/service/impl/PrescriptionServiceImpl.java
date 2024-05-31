package com.example.diagnosisservice.service.impl;

import com.example.diagnosisservice.model.Prescription;
import com.example.diagnosisservice.payload.AppResponse;
import com.example.diagnosisservice.repository.PrescriptionRepository;
import com.example.diagnosisservice.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    PrescriptionRepository rPre;

    @Override
    public List<Prescription> findAllPrescription() {
        return rPre.findAll();
    }

    @Override
    public AppResponse addNewPrescription(Prescription request) {
        try {
            if (ObjectUtils.isEmpty(request)) {
                return new AppResponse("Không được để trống", false);
            }

            return new AppResponse(rPre.save(request));
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã có lỗi xảy ra", false);
        }
    }
}
