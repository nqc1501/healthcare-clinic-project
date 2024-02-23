package com.example.supplyservice.services;

import com.example.supplyservice.models.Supply;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SupplyService {

    List<Supply> findAllSupplies();
}
