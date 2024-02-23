package com.example.supplyservice.services.impl;

import com.example.supplyservice.models.Supply;
import com.example.supplyservice.repositories.SupplyRepository;
import com.example.supplyservice.services.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    SupplyRepository rSupply;

    @Override
    public List<Supply> findAllSupplies() {
        return rSupply.findAll();
    }
}
