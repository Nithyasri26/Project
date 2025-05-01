package com.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizOptionDTO {

    
    @NotBlank(message = "Option ID cannot be empty")
    private String optionId;

    @NotBlank(message = "Question ID cannot be empty")
    private String questionId;

    @NotBlank(message = "Option 1 cannot be empty")
    @Size(max = 255, message = "Option 1 cannot exceed 255 characters")
    private String option1;

    @NotBlank(message = "Option 2 cannot be empty")
    @Size(max = 255, message = "Option 2 cannot exceed 255 characters")
    private String option2;

    @NotBlank(message = "Option 3 cannot be empty")
    @Size(max = 255, message = "Option 3 cannot exceed 255 characters")
    private String option3;

    @NotBlank(message = "Option 4 cannot be empty")
    @Size(max = 255, message = "Option 4 cannot exceed 255 characters")
    private String option4;
}

