package com.example.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.Paymentdto;
import com.example.entity.Payment;
import com.example.mapper.PaymentMapper;
import com.example.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private PaymentMapper paymentMapper;

//    public PaymentServiceImpl(PaymentRepository paymentRepository) {
//        this.paymentRepository = paymentRepository;
//    }

    @Override
    public Paymentdto processPayment(Paymentdto paymentdto) {
        paymentdto.setTransactionId(UUID.randomUUID().toString()); // Generate unique transaction ID
        paymentdto.setStatus("SUCCESS"); // Assume success for now
        paymentdto.setPaymentDate(new Date());
        
        if (paymentdto.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid payment amount.");
        }
        
//        if (!paymentdto.getPaymentMethod().equals("UPI") && !paymentdto.getPaymentMethod().equals("CREDIT_CARD") && !paymentdto.getPaymentMethod().equals("DEBIT_CARD")) {
//            throw new IllegalArgumentException("Invalid payment method: " + paymentdto);
//        }
        
        Payment payment=paymentMapper.convertToEntity(paymentdto);
        payment=paymentRepository.save(payment);
       
        return paymentMapper.convertToDto(payment);
    }

    @Override
    public List<Paymentdto> getPaymentByTransactionId(String transactionId) {
    	List<Payment> payments=paymentRepository.findByTransactionId(transactionId);
    	
    	if (payments.isEmpty()) {
            throw new RuntimeException("No payments found with Transaction ID: " + transactionId);
        }

        return payments.stream()
                       .map(paymentMapper::convertToDto)
                       .collect(Collectors.toList());
    }

    @Override
    public List<Paymentdto> getAllPayments() {
    	 List<Payment> payments = paymentRepository.findAll();
    	    
    	    return payments.stream()
    	                   .map(paymentMapper::convertToDto)  // Convert each Payment to Paymentdto
    	                   .collect(Collectors.toList());
    }
    
//    @Override
//    public List<Paymentdto> getPaymentsByDate(Date startDate, Date endDate) {
//        List<Payment> payments = paymentRepository.findByPaymentDateBetween(startDate, endDate);
//        return payments.stream()
//                       .map(paymentMapper::convertToDto)
//                       .collect(Collectors.toList());
//    }
}
