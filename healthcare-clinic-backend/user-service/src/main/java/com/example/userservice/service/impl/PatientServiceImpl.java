package com.example.userservice.service.impl;

import com.example.responsehandling.payload.response.AppResponse;
import com.example.userservice.model.Patient;
import com.example.userservice.repository.PatientRepository;
import com.example.userservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository rPatient;

    @Override
    public List<Patient> getAllPatient() {
        return rPatient.findAll();
    }

    @Override
    public Patient getById(String id) {
        return rPatient.findById(id).orElse(null);
    }

    @Override
    public AppResponse addPatient(Patient patient) {
        return null;
    }

    @Override
    public AppResponse updatePatient(Patient patient) {
        try {
            Patient existingPatient = rPatient.findById(patient.getId()).orElse(null);
            if (existingPatient == null) {
                return new AppResponse("Bệnh nhân này không tồn tại", false);
            }

            rPatient.save(patient);

            return new AppResponse("Chỉnh sửa thông tin bệnh nhân thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    @Override
    public AppResponse deletePatient(String id) {
        try {
            Patient existingPatient = rPatient.findById(id).orElse(null);
            if (existingPatient == null) {
                return new AppResponse("Bệnh nhân này không tồn tại", false);
            }

            rPatient.delete(existingPatient);

            return new AppResponse("Xóa bệnh nhân thành công", true);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
