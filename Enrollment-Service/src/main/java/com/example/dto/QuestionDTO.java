package com.example.dto;

import java.util.List;

import com.example.entities.QuizOptionDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    @NotBlank(message = "Question ID cannot be empty")
    private String questionId;

    @NotBlank(message = "Question text cannot be empty")
    @Size(min = 5, max = 500, message = "Question text must be between 5 and 500 characters")
    private String questionText;

    @NotBlank(message = "Quiz ID cannot be empty")
    private String quizId;

    @NotBlank(message = "Correct answer cannot be empty")
    private String correctAnswer;

    private List<QuizOptionDTO> options; 
}

