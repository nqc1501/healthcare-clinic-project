package com.example.billingservice.controller;

import com.example.billingservice.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {

    @Autowired
    PaymentService sPayment;

    @GetMapping("/vnpay")
    public ResponseEntity<?> pay(@RequestParam Long amount, @RequestParam Integer billId) {
        return ResponseEntity.ok(sPayment.createVnPayPayment(amount, billId));
    }

    @GetMapping("/vnpay-callback")
    public ResponseEntity<?> payCallBackHandler(@RequestParam Map<String, String> queryParams) {
        return ResponseEntity.ok(sPayment.paymentCallback(queryParams));
    }
}
