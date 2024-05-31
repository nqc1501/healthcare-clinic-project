package com.example.supplyservice.service;

import com.example.supplyservice.model.Medication;
import com.example.supplyservice.model.Supply;
import com.example.supplyservice.payload.AppResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplyService {

    // supply
    List<Supply> findAllSupplies();

    AppResponse addNewSupply(Supply request);

    AppResponse updateSupply(Supply request);

    // medication
     List<Medication> findAllMedication();

    AppResponse addNewMedication(Medication request);

    AppResponse updateMedication(Medication request);
}
