package com.example.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dto.Paymentdto;
import com.example.entity.Payment;
import com.example.repository.PaymentRepository;

@Component
public class PaymentMapper {
	@Autowired
	private PaymentRepository paymentRepo;
	
	public Paymentdto convertToDto(Payment payment) {
		Paymentdto dto=new Paymentdto();
		
		dto.setTransactionId(payment.getTransactionId());
		dto.setUserId(payment.getUserId());
		dto.setStatus(payment.getStatus());
		dto.setPaymentMethod(payment.getPaymentMethod());
		dto.setPaymentDate(payment.getPaymentDate());
		dto.setAmount(payment.getAmount());
		
		paymentRepo.save(payment);
		return dto;
	}
	
	public Payment convertToEntity(Paymentdto paymentdto) {
		Payment entity=new Payment();
		if(paymentdto==null) {
			return null;
		}
		
		entity.setTransactionId(paymentdto.getTransactionId());
		entity.setUserId(paymentdto.getUserId());
		entity.setStatus(paymentdto.getStatus());
		entity.setPaymentMethod(paymentdto.getPaymentMethod());
		entity.setPaymentDate(paymentdto.getPaymentDate());
		entity.setAmount(paymentdto.getAmount());
		
		return entity;
	}

}
