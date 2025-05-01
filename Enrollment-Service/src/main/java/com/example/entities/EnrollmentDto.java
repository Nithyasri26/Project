package com.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDto {

	private int studentId;
    private String studentName;
    private String courseName;
    private String email;
    private String password;
    private Long mobileNumber;
    private String otp;
//	public void setStudentId(int studentId2) {
//		// TODO Auto-generated method stub
//		
//	}
	
}
