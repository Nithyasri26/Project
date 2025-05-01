package com.example.dto;

import java.util.List;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
	
	private String quizId;
    private String title;
    private int marks;
    
    private String courseId;
	


}
