package com.example.billingservice.service.impl;

import com.example.billingservice.config.PaymentConfig;
import com.example.billingservice.model.Bill;
import com.example.billingservice.repository.BillRepository;
import com.example.billingservice.service.PaymentService;
import com.example.responsehandling.payload.response.AppResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentConfig config;

    @Autowired
    private BillRepository rBill;

    @Override
    public AppResponse createVnPayPayment(Long price, Integer billId) {

        try {
            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String orderType = "other";
            long amount = price * 100;
            String bankCode = "NCB";

            String vnp_TxnRef = config.getRandomNumber(8);
            String vnp_IpAddr = "127.0.0.1";

            String vnp_TmnCode = config.vnp_TmnCode;

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_CurrCode", "VND");

            if (bankCode != null && !bankCode.isEmpty()) {
                vnp_Params.put("vnp_BankCode", bankCode);
            }
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
            vnp_Params.put("vnp_OrderType", orderType);

            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_ReturnUrl", config.vnp_ReturnUrl + "?billId=" + billId);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            String vnp_SecureHash = config.hmacSHA512(config.secretKey, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = config.vnp_PayUrl + "?" + queryUrl;

            return new AppResponse(paymentUrl);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã có lỗi xảy ra", false);
        }
    }

    @Override
    public AppResponse paymentCallback(Map<String, String> queryParams) {

        try {
            String vnp_ResponseCode = queryParams.get("vnp_ResponseCode");
            if (vnp_ResponseCode.equals("00")) {
                Bill bill = rBill.findById(Integer.parseInt(queryParams.get("billId"))).orElse(null);
                if (bill == null) {
                    return new AppResponse("Hóa đơn không tồn tại", false);
                }
                return new AppResponse("Giao dịch thành công", true);
            }

            return new AppResponse("Giao dịch thất bại", false);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new AppResponse("Đã có lỗi xảy ra", false);
        }
    }
}
