package com.example.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String>{

	List<Payment> findByTransactionId(String transactionId);
	
}
