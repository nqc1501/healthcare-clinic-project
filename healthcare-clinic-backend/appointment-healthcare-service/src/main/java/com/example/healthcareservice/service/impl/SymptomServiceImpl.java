package com.example.healthcareservice.service.impl;

import com.example.healthcareservice.model.Symptom;
import com.example.healthcareservice.repository.SymptomRepository;
import com.example.healthcareservice.service.SymptomService;
import com.example.responsehandling.payload.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class SymptomServiceImpl implements SymptomService {

    @Autowired
    SymptomRepository rSymptom;

    @Override
    public List<Symptom> getByPatientId(String patientId) {
        return rSymptom.findByPatientId(patientId);
    }

    @Override
    public AppResponse addSymptom(String patientId, List<Symptom> listSymptom) {
        try {

            for (Symptom s : listSymptom) {
                s.setPatientId(patientId);
                rSymptom.save(s);
            }

            return new AppResponse("Thêm triệu chứng cho người dùng thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
