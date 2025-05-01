package com.example.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.Paymentdto;
import com.example.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/processPayment")
    public ResponseEntity<Paymentdto> processPayment(@RequestBody Paymentdto paymentdto){
		return new ResponseEntity<Paymentdto>(paymentService.processPayment(paymentdto),HttpStatus.OK);
	}
	
	 @GetMapping("/getPaymentByTransactionId/{transactionId}")
	    public ResponseEntity<List<Paymentdto>> getPaymentByTransactionId(@PathVariable(name="transactionId") String transactionId) {
		 return new ResponseEntity<List<Paymentdto>>(paymentService.getPaymentByTransactionId(transactionId),HttpStatus.OK);
	 }

	 @GetMapping("/getAllPayments")
	    public ResponseEntity<List<Paymentdto>> getAllPayments() {
	    return new ResponseEntity<List<Paymentdto>>(paymentService.getAllPayments(),HttpStatus.OK);
	 }
	 
//	 @GetMapping("/getPaymentsByDate/{startDate}/{endDate}")
//	 public ResponseEntity<List<Paymentdto>> getPaymentsByDate(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy")Date startDate,@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate){
//		 
//		 return new ResponseEntity<List<Paymentdto>>(paymentService.getPaymentsByDate(startDate, endDate),HttpStatus.OK);
//	 }
//	 @PostMapping("/upi")
//	 public String initiateUPIPayment(@RequestParam double amount) throws Exception {
//	        return paymentService.createUPIPayment(amount);
//	    }
}
