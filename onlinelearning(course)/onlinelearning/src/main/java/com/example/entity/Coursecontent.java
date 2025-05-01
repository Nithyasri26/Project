package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coursecontent {
    @Id
    @NotBlank(message = "ContentId cannot be empty")
    private String contentId;
    
    @NotBlank(message="ContentType cannot be empty")
    private String contentType;
    
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course courseObj;
    
    @NotBlank(message = "UserId cannot be empty")
    private String userId;
    
    @Min(value = 0, message = "rating cannot be negative")
    @Max(value = 5, message = "rating cannot exceed 5")
    private float rating;
    
    @Size(max = 500, message = "feedback cannot exceed 500 characters")
    private String feedback;
    
    @Size(max = 500, message = "description cannot exceed 500 characters")
    @NotBlank(message = "Description cannot be empty")
    private String description;
    
    @Size(max = 500, message = "issueDescription cannot exceed 500 characters")
    private String issueDescription;
 
    @NotBlank(message = "completed cannot be empty")
    private String completed;
}