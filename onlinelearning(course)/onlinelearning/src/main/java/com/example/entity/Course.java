package com.example.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {
    @Id
    @NotNull(message = "courseID cannot be null")
    private String courseId;
    
    @NotBlank(message = "Course name cannot be empty")
    @Size(max = 100, message = "Course name cannot exceed 100 characters")
    private String courseName;
    
    @NotBlank(message = "Description cannot be empty")
    private String description;
    
    @DecimalMin(value = "0.1", message = "Price must be greater than 0")
    private BigDecimal price;  // Changed float to BigDecimal
    
    @Min(value = 1, message = "Duration must be at least 1 hour")
    @Max(value = 1000, message = "Duration cannot exceed 1000 hours")
    private int duration;
    
    @NotBlank(message = "Category cannot be empty")
    private String category;
    
    @Min(value = 0, message = "Rating cannot be negative")
    @Max(value = 5, message = "Rating cannot exceed 5")
    private float rating;
    
    @NotBlank(message = "Difficulty cannot be empty")
    private String difficulty;
    
    @OneToMany(mappedBy = "courseObj")
    @JsonIgnore
    private List<Coursecontent> coursecontentList;
}
