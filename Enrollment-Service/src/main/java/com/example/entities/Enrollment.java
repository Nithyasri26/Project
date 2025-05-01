package com.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Students")
public class Enrollment {
	
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int studentId;
	  
	  @NotBlank(message = "Student name is required")
	    private String studentName;
	  
	  @NotBlank(message = "Course name is required")
	    private String courseName;
	  
	   @Email(message = "Invalid email format")
	   @NotBlank(message = "Email is required")
	   @Column(unique = true)
	    private String email;
	   
	   @NotBlank(message = "Password is required")
	   @Size(min = 6, message = "Password must be at least 6 characters long")
	    private String password;
	   
	   @NotNull(message = "Mobile number cannot be null")
	   @Digits(integer = 10, fraction = 0, message = "Mobile number must be 10 digits")
	    private Long mobileNumber;
	   
	   
//	   @ManyToOne
//	    @JoinColumn(name = "course_id")  // Ensure proper mapping with Course entity
//	    private Course course; 
	   
	   private String courseId;
	   
	   
	    private String otp;
	    
	}











