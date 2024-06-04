package com.example.healthcareservice.service;

import com.example.healthcareservice.model.item.Supply;
import com.example.responsehandling.payload.response.AppResponse;

import java.util.List;

public interface SupplyService {

    List<Supply> getAllSupply();

    AppResponse addSupply(Supply supply);

    List<Supply> addSupplyToRoom(Integer roomId);
}
