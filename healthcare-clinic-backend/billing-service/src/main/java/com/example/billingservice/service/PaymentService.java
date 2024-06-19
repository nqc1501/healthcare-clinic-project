package com.example.billingservice.service;

import com.example.responsehandling.payload.response.AppResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface PaymentService {

    AppResponse createVnPayPayment(Long price, Integer billId);

    AppResponse paymentCallback(Map<String, String> queryParams);
}
