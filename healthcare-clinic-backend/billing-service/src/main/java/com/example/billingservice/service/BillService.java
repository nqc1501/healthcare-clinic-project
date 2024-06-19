package com.example.billingservice.service;

import com.example.billingservice.model.Bill;
import com.example.responsehandling.payload.response.AppResponse;

import java.util.List;

public interface BillService {

    List<Bill> getBillByPatient(String patientId);

    AppResponse addBill(Bill bill);
}
