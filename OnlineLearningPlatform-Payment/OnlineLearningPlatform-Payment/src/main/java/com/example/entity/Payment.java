package com.example.entity;


import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Payment {
	
	@Id
	private String transactionId;
	private String userId;
	private Double amount;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss", timezone = "UTC")
	private Date paymentDate; // Stores date and time of payment
	private String status;
	
	 @Enumerated(EnumType.STRING) // This ensures it stores the String instead of the ordinal index
	private PaymentMethod paymentMethod; // Updated to use Enum
	
	@PrePersist
    protected void onCreate() {
        this.paymentDate = new Date();  // Auto-set the current date
    }
}
