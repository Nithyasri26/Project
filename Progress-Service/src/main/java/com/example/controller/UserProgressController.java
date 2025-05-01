package com.example.controller;

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
import com.example.entity.*;
import com.example.exception.UserProgressNotFoundException;

import java.util.List;

import com.example.service.UserProgressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/progress")
public class UserProgressController {
	
	@Autowired
	private UserProgressService progressService;

	@GetMapping("/course/{courseId}")
	public ResponseEntity<List<Progress>> getProgressByCourse(@PathVariable String courseId) throws UserProgressNotFoundException {
		return new ResponseEntity<List<Progress>>(progressService.getProgressByCourse(courseId),HttpStatus.OK);
	}
	 
	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<List<Progress>>getProgressByQuiz(@PathVariable String quizId) throws UserProgressNotFoundException {
		return new ResponseEntity<List<Progress>>( progressService.getProgressByQuiz(quizId),HttpStatus.OK);
	}
	   

//	@GetMapping("/getprogress/{id}")
//	public ResponseEntity<Progress> getProgressByUserId(@PathVariable int id) throws UserProgressNotFoundException {
//	   return new ResponseEntity<>(progressService.getProgressByUserId(id),HttpStatus.OK);
//	}
	
	@GetMapping("/getAllProgress")
    public ResponseEntity<List<Progress>> getAllProgress() throws UserProgressNotFoundException {
        return new ResponseEntity<List<Progress>>(progressService.getAllProgress(),HttpStatus.OK);
    }
  

	@PostMapping("/createProgress")
    public ResponseEntity<Progress> createProgress(@RequestBody @Valid Progress progress) {
		return new ResponseEntity<>(progressService.createProgress(progress),HttpStatus.OK);
	}
	    
	@PutMapping("/updateProgress/{id}")
    public ResponseEntity<Progress> updateProgress(@PathVariable int id, @RequestBody @Valid Progress progress) throws UserProgressNotFoundException {
		return new ResponseEntity<>(progressService.updateProgress(id, progress),HttpStatus.OK);
	}


	@DeleteMapping("/deleteProgress/{id}")
	public ResponseEntity<String> deleteProgress(@PathVariable int id) throws UserProgressNotFoundException {
	    progressService.deleteProgress(id);
	    return new ResponseEntity<>("Progress deleted successfully!", HttpStatus.OK);
	}
	
	    
	@GetMapping("/getcompletedusers")
	public ResponseEntity<List<Progress>> getCompletedUsers() throws UserProgressNotFoundException {
	   return new ResponseEntity<>(progressService.getCompletedUsers(),HttpStatus.OK);
	}

}

	   
	    


