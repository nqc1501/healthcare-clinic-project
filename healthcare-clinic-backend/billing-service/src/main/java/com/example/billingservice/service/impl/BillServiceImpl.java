package com.example.billingservice.service.impl;

import com.example.billingservice.model.Bill;
import com.example.billingservice.repository.BillRepository;
import com.example.billingservice.service.BillService;
import com.example.responsehandling.payload.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepository rBill;

    @Override
    public List<Bill> getBillByPatient(String patientId) {
        return rBill.findByPatientId(patientId);
    }

    @Override
    public AppResponse addBill(Bill bill) {
        return new AppResponse(rBill.save(bill));
    }
}
