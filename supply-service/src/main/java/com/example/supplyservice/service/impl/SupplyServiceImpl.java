package com.example.supplyservice.service.impl;

import com.example.supplyservice.model.Medication;
import com.example.supplyservice.model.Supply;
import com.example.supplyservice.payload.AppResponse;
import com.example.supplyservice.repository.MedicationRepository;
import com.example.supplyservice.repository.SupplyRepository;
import com.example.supplyservice.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    SupplyRepository rSup;

    @Autowired
    MedicationRepository rMed;

    // supply
    @Override
    public List<Supply> findAllSupplies() {
        return rSup.findAll();
    }

    @Override
    public AppResponse addNewSupply(Supply request) {
        rSup.save(request);
        return new AppResponse("Thêm thiết bị thành công", true);
    }

    @Override
    public AppResponse updateSupply(Supply request) {
        try {
            Optional<Supply> existingSupplyOptional = rSup.findById(request.getId());
            if (existingSupplyOptional.isEmpty()) {
                return new AppResponse("Thiết bị không tồn tại", false);
            }

            rSup.save(request);
            return new AppResponse("Chỉnh sửa thông tin thiết bị thành công", true);
        } catch(Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }

    // medication
    @Override
    public List<Medication> findAllMedication() {
        return rMed.findAll();
    }

    @Override
    public AppResponse addNewMedication(Medication request) {
        rMed.save(request);
        return new AppResponse("Thêm thuốc thành công", true);
    }

    @Override
    public AppResponse updateMedication(Medication request) {
        try {
            Optional<Medication> existingSupplyOptional = rMed.findById(request.getId());
            if (existingSupplyOptional.isEmpty()) {
                return new AppResponse("Thuốc không tồn tại", false);
            }

            rMed.save(request);
            return new AppResponse("Chỉnh sửa thông tin thuốc thành công", true);
        } catch(Exception e) {
            e.printStackTrace();
            return new AppResponse("Đã xảy ra lỗi", false);
        }
    }
}
