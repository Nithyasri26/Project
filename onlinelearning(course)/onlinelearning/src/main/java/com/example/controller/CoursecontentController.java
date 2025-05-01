package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Service.CourseContentService;
import com.example.entity.Coursecontent;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/coursecontent")
public class CoursecontentController {

    @Autowired
    private CourseContentService courseContentService;

    
    @PostMapping("/create")
    public ResponseEntity<Coursecontent> addContentToCourse(@RequestParam String courseId,@Valid @RequestBody Coursecontent content) {
        // Add content and associate it with the course
        Coursecontent createdContent = courseContentService.addContentToCourse(courseId, content);
        return new ResponseEntity<>(createdContent, HttpStatus.CREATED);   
    }

  
    @PutMapping("/update/{contentId}")
    public ResponseEntity<Coursecontent> updateContent(@PathVariable String contentId,@Valid @RequestBody Coursecontent content) {
        Coursecontent updatedContent = courseContentService.updateContent(contentId, content);
        return new ResponseEntity<>(updatedContent, HttpStatus.OK);  
    }

    @DeleteMapping("/delete/{contentId}")
    public ResponseEntity<Void> deleteContent(@PathVariable String contentId) {
        courseContentService.deleteContent(contentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }

    @PutMapping("/markasComplete/{userId}/{contentId}")
    public ResponseEntity<Void> markContentAsCompleted(@PathVariable String userId, @PathVariable String contentId,@RequestParam String Completed) {
        courseContentService.markContentAsCompleted(userId, contentId,Completed);
        return new ResponseEntity<>(HttpStatus.OK);  
    }

    @PutMapping("/reportcontent/{userId}/{contentId}")
    public ResponseEntity<Void> reportContentIssue(@PathVariable String userId, @PathVariable String contentId, @RequestParam String issueDescription) {
        courseContentService.reportContentIssue(userId, contentId, issueDescription);
        return new ResponseEntity<>(HttpStatus.OK);  
    }
    
    @GetMapping("/getAllcontent")
    public ResponseEntity<List<Coursecontent>> getAllContent(@RequestParam String courseId){
    	List<Coursecontent> contents=courseContentService.getAllcontent(courseId);
    	return new ResponseEntity<>(contents,HttpStatus.OK);
    }
}
