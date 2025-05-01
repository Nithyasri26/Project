package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Progress {
	
	@Id
	@NotNull(message="ID cannot be null")
	private int id;
	@NotBlank(message="Course ID cannot be blank")
	private String courseId;
	@NotBlank(message="Quiz ID cannot be blank")
	private String quizId;
	@Min(value=0, message="Progress percentage cannot be less than 0")
	@Max(value=100, message="Progress percentage cannot be more than 100")
	private float progressPercentage;
	@NotBlank(message="Completion status cannot be blank")
	private String completionStatus;
	
	

}
