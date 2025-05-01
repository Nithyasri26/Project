package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.CourseDTO;
import com.example.entities.Enrollment;
import com.example.entities.EnrollmentDto;
import com.example.exception.InvalidCourseException;
import com.example.exception.InvalidEnrollmentException;
import com.example.exception.ResourceNotFoundException;
import com.example.microservicecalls.CourseClient;
import com.example.repository.EnrollmentRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
  
    
    private final Random random = new Random();

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }
    

    @Override
    public Enrollment getEnrollmentById(int studentId) throws ResourceNotFoundException {
        return enrollmentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("StudentId is not found"));
    }
    

    @Override
    public Enrollment updateEnrollment(int studentId, Enrollment enrollmentDetails) throws ResourceNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student ID not found"));

        enrollment.setStudentName(enrollmentDetails.getStudentName());
        enrollment.setCourseName(enrollmentDetails.getCourseName());
        enrollment.setEmail(enrollmentDetails.getEmail());
        enrollment.setMobileNumber(enrollmentDetails.getMobileNumber());

        return enrollmentRepository.save(enrollment);
    }
    

    @Override
    public Enrollment deleteEnrollment(int studentId) throws ResourceNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student ID not found"));

        enrollmentRepository.delete(enrollment);
        return enrollment; // Return deleted entity
    }
    
    private static final List<String> ALLOWED_COURSES = List.of("Java", "Python", "SQL", "Web Development", "Backend");
   
//    public void validateCourse(String courseName)throws InvalidCourseException {
//        if (!ALLOWED_COURSES.contains(courseName)) {
//            throw new InvalidCourseException("Invalid course selection! Choose only from: " + ALLOWED_COURSES);
//        }
//    }

    
    public void validateCourse(String courseName) throws InvalidCourseException {
        boolean isValid = ALLOWED_COURSES.stream()
                .anyMatch(allowed -> allowed.equalsIgnoreCase(courseName));

        if (!isValid) {
            throw new InvalidCourseException("Invalid course selection! Choose only from: " + ALLOWED_COURSES);
        }
    }
  
    
    public List<String> getCourses() {
        return ALLOWED_COURSES;
    }

 
    
    private final Map<Long, String> otpStorage = new HashMap<>();
    
    public String generateOtp(Long mobileNumber) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000); // Generates 6-digit OTP
        otpStorage.put(mobileNumber, otp); // Store OTP temporarily
        return otp;
    }

    
    
    public boolean verifyOtp(Long mobileNumber, String userEnteredOtp) {
        String storedOtp = otpStorage.get(mobileNumber); // Get stored OTP
        return storedOtp != null && storedOtp.equals(userEnteredOtp); // Verify OTP
    }

    
    
    
    @Override
    public EnrollmentDto createEnrollment(Enrollment enrollment) throws InvalidEnrollmentException {
    	validateCourse(enrollment.getCourseName());
        Optional<Enrollment> optional = enrollmentRepository.findByMobileNumber(enrollment.getMobileNumber());
        if (optional.isPresent()) {
            throw new InvalidEnrollmentException("Already Enrolled with this Mobile Number");
        }

        // Generate OTP
        String otp = generateOtp(enrollment.getMobileNumber());
        enrollment.setOtp(otp);

        // Simulating sending OTP (In real case, integrate SMS API)
        System.out.println("OTP sent to " + enrollment.getMobileNumber() + ": " + otp);

        // Verify OTP (Assume frontend sends back the OTP to verify)
        if (!verifyOtp(enrollment.getMobileNumber(), otp)) {
            throw new InvalidEnrollmentException("OTP verification failed");
        }

        // Save enrollment only after OTP verification
        enrollmentRepository.save(enrollment);

        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setCourseName(enrollment.getCourseName());
        enrollmentDto.setEmail(enrollment.getEmail());
        enrollmentDto.setMobileNumber(enrollment.getMobileNumber());
        enrollmentDto.setStudentName(enrollment.getStudentName());
        enrollmentDto.setPassword(enrollment.getPassword());
        enrollmentDto.setStudentId(enrollment.getStudentId());
        enrollmentDto.setOtp(otp);

        return enrollmentDto;
    }

	

}

