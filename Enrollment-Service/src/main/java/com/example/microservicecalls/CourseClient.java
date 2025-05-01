package com.example.microservicecalls;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.dto.CourseDTO;

@FeignClient(name = "COURSE-SERVICE")  
public interface CourseClient {
    @GetMapping("/course/searchcoursebyId/{courseId}")  
    CourseDTO getCourseById(@PathVariable("courseId") String courseId);

	
}
