package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CourseDTO;
import com.example.dto.QuestionDTO;
import com.example.dto.QuizDTO;
import com.example.entities.Enrollment;
import com.example.entities.EnrollmentDto;
import com.example.exception.InvalidEnrollmentException;
import com.example.exception.ResourceNotFoundException;
import com.example.microservicecalls.CourseClient;
import com.example.microservicecalls.QuizClient;
import com.example.service.EnrollmentService;
import com.example.service.EnrollmentServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	CourseClient courseClient;
	
	 @Autowired
	 private QuizClient quizClient;
	 

	    @PostMapping("/createEnrollment")
	    public ResponseEntity<EnrollmentDto> createEnrollment(@Valid @RequestBody Enrollment enrollment) throws InvalidEnrollmentException {
	        return new ResponseEntity<EnrollmentDto>(enrollmentService.createEnrollment(enrollment), HttpStatus.OK);
	    }
	   

	    @GetMapping("/getAllEnrollments")
	    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
	        return new ResponseEntity<List<Enrollment>>(enrollmentService.getAllEnrollments(),HttpStatus.OK);
	    }
	    

	    @GetMapping("/getEnrollmentById/{studentId}")
	    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable("studentId") int studentId) throws  ResourceNotFoundException {
	        return new ResponseEntity<Enrollment>(enrollmentService.getEnrollmentById(studentId), HttpStatus.OK);
	    }
	    
	    
	    @GetMapping("/generateOtp/{mobileNumber}")
	    public ResponseEntity<String> generateOtp(@PathVariable("mobileNumber") long mobileNumber) throws ResourceNotFoundException{
	    	return new ResponseEntity<String>(enrollmentService.generateOtp(mobileNumber),HttpStatus.OK);
	    }
	    
	    
	    @GetMapping("/verifyOtp/{mobileNumber}/{otp}")
	    public ResponseEntity<Boolean>verifyOtp(@PathVariable("mobileNumber") long mobileNumber,@PathVariable("otp") String otp)throws ResourceNotFoundException{
	    	return new ResponseEntity<Boolean>(enrollmentService.verifyOtp(mobileNumber, otp),HttpStatus.OK);
	    }

	    @PutMapping("/updateEnrollment/{studentId}")
	    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable("studentId") int studentId, @RequestBody Enrollment enrollmentDetails)throws  ResourceNotFoundException {
	       return new ResponseEntity<Enrollment>(enrollmentService.updateEnrollment(studentId, enrollmentDetails), HttpStatus.OK);
	    }
	    

	    @DeleteMapping("/deleteEnrollment/{studentId}")
	    public ResponseEntity<String> deleteEnrollment(@PathVariable("studentId") int studentId) throws  ResourceNotFoundException{
	        enrollmentService.deleteEnrollment(studentId);
	        return new ResponseEntity<String>("Enrollment deleted successfully", HttpStatus.OK);
	    }
	   

	    @PostMapping("/validate")
	    public String validateCourse(@RequestParam String courseName) {
	    	enrollmentService.validateCourse(courseName);
	        return "Valid course selection: " + courseName;
	    }
	    
	    
	    @PostMapping("/verifyOtp")
	    public ResponseEntity<String> verifyOtp(@RequestParam Long mobileNumber, @RequestParam String otp) throws ResourceNotFoundException {
	        boolean isVerified = enrollmentService.verifyOtp(mobileNumber, otp);
	        if (isVerified) {
	            return ResponseEntity.ok("OTP verified successfully!");
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
	        }
	    }
	    
	    // Feign Methods calling from different Microservices
	    
	    @GetMapping("/searchcoursebyId/{courseId}")
	    public CourseDTO getCourse(@PathVariable("courseId") String courseId){
	    	return courseClient.getCourseById(courseId);
	    }
	    
	   
	    @GetMapping("/viewAllQuizzes")
		 public ResponseEntity<List<QuizDTO>> getAllQuizzes(){
	    	return quizClient.getAllQuizzes();
	    }
	    
	    
	    @GetMapping("/viewAllQuestions")
	    public ResponseEntity<List<QuestionDTO>> getAllQuestions(){
	    	return quizClient.getAllQuestions();
	    }
		
		@GetMapping("/viewQuestionById/{questionId}")
	    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable("questionId") String questionId){
			return quizClient.getQuestionById(questionId);
		}
		
		@GetMapping("/searchQuestion/{keyword}")
	    public ResponseEntity<List<QuestionDTO>> searchQuestionsByKeyword(@PathVariable("keyword") String keyword){
			return quizClient.searchQuestionsByKeyword(keyword);
		}
		
		@GetMapping("/searchByQuizId/{quizId}")
	    public ResponseEntity<List<QuestionDTO>> searchQuestionsByQuizId(@PathVariable("quizId") String quizId){
			return quizClient.searchQuestionsByQuizId(quizId);
		}
//		
//		

}


