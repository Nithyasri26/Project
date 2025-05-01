package com.example.dto;



import java.util.Date;

import com.example.entity.PaymentMethod;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paymentdto {
	private String transactionId;
	private String userId;
	private Double amount;
	private Date paymentDate;
	private String status;
	private PaymentMethod paymentMethod; // Updated to use Enum
}
