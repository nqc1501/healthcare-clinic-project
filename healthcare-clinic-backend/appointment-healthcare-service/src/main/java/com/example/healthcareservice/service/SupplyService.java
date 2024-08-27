package com.example.healthcareservice.service;

import com.example.healthcareservice.model.item.Supply;
import com.example.healthcareservice.payload.req.SupplyRequest;
import com.example.responsehandling.payload.response.AppResponse;

import java.util.List;

public interface SupplyService {

    List<Supply> getAllSupply();

    AppResponse addSupply(Supply supply);

    AppResponse addSupplyToRoom(Integer roomId, SupplyRequest request);

    AppResponse updateSupply(Supply supply);

    AppResponse deleteSupply(Integer id);
}
