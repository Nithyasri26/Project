package com.example.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.dto.CourseDTO;
import com.example.entities.Enrollment;
import com.example.entities.EnrollmentDto;
import com.example.exception.InvalidCourseException;
import com.example.exception.InvalidEnrollmentException;
import com.example.microservicecalls.CourseClient;
import com.example.repository.EnrollmentRepository;
import com.example.service.EnrollmentServiceImpl;

@ExtendWith(MockitoExtension.class)  // Enables Mockito
class EnrollmentServiceImplTest {

    @InjectMocks
    private EnrollmentServiceImpl enrollmentService; // Class to be tested

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private CourseClient courseClient;

    private Enrollment enrollment;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        enrollment = new Enrollment();
        enrollment.setStudentId(101);
        enrollment.setStudentName("John Doe");
        enrollment.setCourseName("Java");
        enrollment.setEmail("john@example.com");
        enrollment.setMobileNumber(9876543210L);
        enrollment.setPassword("password123");
    }

    // Test for valid course selection
    @Test
    void testValidateCourseSuccess() {
        assertDoesNotThrow(() -> enrollmentService.validateCourse("Java"));
    }

    //  Test for invalid course selection (Exception expected)
    @Test
    void testValidateCourseFailure() {
        assertThrows(InvalidCourseException.class, () -> {
            enrollmentService.validateCourse("C++");
        });
    }

    //  Test for successful enrollment
    @Test
    void testCreateEnrollmentSuccess() throws InvalidEnrollmentException {
        when(enrollmentRepository.findByMobileNumber(anyLong())).thenReturn(Optional.empty()); // No existing enrollment
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(enrollment);

        EnrollmentDto result = enrollmentService.createEnrollment(enrollment);

        assertNotNull(result);
        assertEquals(enrollment.getStudentName(), result.getStudentName());
        assertEquals(enrollment.getCourseName(), result.getCourseName());
        verify(enrollmentRepository, times(1)).save(any(Enrollment.class));
    }

    //  Test for duplicate enrollment (Mobile Number already exists)
    @Test
    void testCreateEnrollmentFailure() {
        when(enrollmentRepository.findByMobileNumber(anyLong())).thenReturn(Optional.of(enrollment));

        assertThrows(InvalidEnrollmentException.class, () -> {
            enrollmentService.createEnrollment(enrollment);
        });

        verify(enrollmentRepository, never()).save(any(Enrollment.class));
    }

    // Test OTP generation
    @Test
    void testGenerateOtp() {
        String otp = enrollmentService.generateOtp(9876543210L);
        assertNotNull(otp);
        assertEquals(6, otp.length()); // OTP should be 6 digits
    }

    //  Test OTP verification (Valid OTP)
    @Test
    void testVerifyOtpSuccess() {
        String otp = enrollmentService.generateOtp(9876543210L);
        assertTrue(enrollmentService.verifyOtp(9876543210L, otp));
    }

    //  Test OTP verification (Invalid OTP)
    @Test
    void testVerifyOtpFailure() {
        enrollmentService.generateOtp(9876543210L);
        assertFalse(enrollmentService.verifyOtp(9876543210L, "123456")); // Wrong OTP
    }
}


