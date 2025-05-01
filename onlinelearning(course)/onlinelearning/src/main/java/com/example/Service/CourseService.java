package com.example.Service;

import java.util.List;
import com.example.entity.Course;
import com.example.exceptions.CourseNotFoundException;

public interface CourseService {
    
    Course createCourse(Course courseObj);
    
    Course updateCourse(String courseId, Course updatedCourse) throws CourseNotFoundException;
    
    void deleteCourse(String courseId) throws CourseNotFoundException;
    
    List<Course> searchCourses(String category, float rating, String difficulty);
    
    List<Course> searchCoursebycourseName(String courseName) throws CourseNotFoundException;
    
    List<Course> filterCoursesByPriceRange(float minPrice, float maxPrice) throws CourseNotFoundException;
    
    Course searchCourseById(String courseId) throws CourseNotFoundException;
    
    List<Course> getAllCourses() throws CourseNotFoundException;

	List<Course> searchCourseByCourseName(String courseName) throws CourseNotFoundException;
}
