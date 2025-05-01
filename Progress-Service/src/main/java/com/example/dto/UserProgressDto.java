package com.example.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserProgressDto {
	private int userId;
	private String courseId;
	private String quizId;
	private float progressPercentage;
	private boolean iscompleted;
	
	

}
