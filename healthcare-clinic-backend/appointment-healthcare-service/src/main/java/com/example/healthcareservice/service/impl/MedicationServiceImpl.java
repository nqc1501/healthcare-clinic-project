package com.example.healthcareservice.service.impl;

import com.example.healthcareservice.model.item.Medication;
import com.example.healthcareservice.model.item.Supply;
import com.example.healthcareservice.repository.MedicationRepository;
import com.example.healthcareservice.service.MedicationService;
import com.example.responsehandling.payload.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService {

    @Autowired
    MedicationRepository rMedication;

    @Override
    public List<Medication> getAllMedication() {
        return rMedication.findAll();
    }

    @Override
    public AppResponse addMedication(Medication medication) {
        try {
            rMedication.save(medication);

            return new AppResponse("Thêm thuốc thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
