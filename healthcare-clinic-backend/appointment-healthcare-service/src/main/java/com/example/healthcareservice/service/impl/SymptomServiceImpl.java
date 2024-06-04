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
    public AppResponse addSymptom(Symptom symptom) {
        try {
            if (ObjectUtils.isEmpty(symptom)) {
                return new AppResponse("Không được để trống", false);
            }

            if (ObjectUtils.isEmpty(symptom.getPatientId())) {
                return new AppResponse("Mã người dùng không được để trống", false);
            }

            rSymptom.save(symptom);

            return new AppResponse("Thêm triệu chứng cho người dùng " + symptom.getPatientId() + " thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
