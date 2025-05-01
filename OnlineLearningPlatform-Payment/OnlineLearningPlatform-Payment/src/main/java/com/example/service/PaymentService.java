package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.dto.Paymentdto;
import com.example.entity.Payment;

import jakarta.persistence.criteria.Order;
@Service
public interface PaymentService {
	
//	@Value("${razorpay.key_id}")
//	private String keyId;
//	
//	@Value("${razorpay.key_secret}")
//	private String keySecret;
//
//    public String createUPIPayment(double amount) throws Exception {
//        RazorpayClient client = new RazorpayClient(keyId, keySecret);
//
//        JSONObject options = new JSONObject();
//        options.put("amount", amount * 100); // Razorpay uses paise        options.put("currency", "INR");
//        options.put("payment_capture", 1);
//        options.put("method", "upi"); // UPI payment methodOrder order = client.orders.create(options);
//        return Order.toJson().toString();
//    }
	

	Paymentdto processPayment(Paymentdto paymentdto);
    List<Paymentdto> getPaymentByTransactionId(String transactionId);
    
    List<Paymentdto> getAllPayments();
}
