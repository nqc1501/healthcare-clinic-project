package com.example.diagnosisservice.service.impl;

import com.example.diagnosisservice.model.Diagnostic;
import com.example.diagnosisservice.payload.AppResponse;
import com.example.diagnosisservice.repository.DiagnosticRepository;
import com.example.diagnosisservice.service.DiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
public class DiagnosticServiceImpl implements DiagnosticService {

    @Autowired
    DiagnosticRepository rDia;

    @Override
    public List<Diagnostic> findAllDiagnostic() {
        return rDia.findAll();
    }

    @Override
    public AppResponse addNewDiagnostic(Diagnostic request) {

        try {
            if (ObjectUtils.isEmpty(request)) {
                return new AppResponse("Không được để trống", false);
            }

            return new AppResponse(rDia.save(request));

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã có lỗi xảy ra", false);
        }
    }
}
