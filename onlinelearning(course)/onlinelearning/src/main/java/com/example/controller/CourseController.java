package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Service.CourseService;
import com.example.entity.Course;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")
public class CourseController {
    
    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
        return new ResponseEntity<>(courseService.createCourse(course), HttpStatus.CREATED);
    }

    @GetMapping("/searchcourses")
    public ResponseEntity<List<Course>> searchCourses(
            @RequestParam String category, 
            @RequestParam float rating, 
            @RequestParam String difficulty) {
        return ResponseEntity.ok(courseService.searchCourses(category, rating, difficulty));
    }

    @PutMapping("/update/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable String courseId, @Valid @RequestBody Course updatedCourse) {
        return ResponseEntity.ok(courseService.updateCourse(courseId, updatedCourse));
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchcoursebyname")
    public ResponseEntity<List<Course>> searchCoursesByCourseName(@RequestParam String courseName) {
        return ResponseEntity.ok(courseService.searchCoursebycourseName(courseName));
    }
    
    @GetMapping("/filtercoursebyprice/{minPrice}/{maxPrice}")
    public ResponseEntity<List<Course>> filterCoursesByPriceRange(@PathVariable float minPrice, @PathVariable float maxPrice) {
        return ResponseEntity.ok(courseService.filterCoursesByPriceRange(minPrice, maxPrice));
    }

    @GetMapping("/searchcoursebyId/{courseId}")
    public ResponseEntity<Course> searchCourseById(@PathVariable String courseId) {
        return ResponseEntity.ok(courseService.searchCourseById(courseId));
    }

    @GetMapping("/getAllCourses")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
}
