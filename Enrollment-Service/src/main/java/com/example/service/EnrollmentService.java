package com.example.service;

import java.util.List;

import com.example.dto.CourseDTO;
import com.example.entities.Enrollment;
import com.example.entities.EnrollmentDto;
import com.example.exception.InvalidCourseException;
import com.example.exception.InvalidEnrollmentException;
import com.example.exception.ResourceNotFoundException;

public interface EnrollmentService {

    List<Enrollment> getAllEnrollments();

    Enrollment getEnrollmentById(int studentId) throws ResourceNotFoundException;

    Enrollment updateEnrollment(int studentId, Enrollment enrollmentDetails) throws ResourceNotFoundException;

    Enrollment deleteEnrollment(int studentId) throws ResourceNotFoundException;

    EnrollmentDto createEnrollment(Enrollment enrollment) throws InvalidEnrollmentException;
    
    void validateCourse(String courseName) throws InvalidCourseException;

    List<String> getCourses();

    String generateOtp(Long mobileNumber);

    boolean verifyOtp(Long mobileNumber, String userEnteredOtp);

	
}
