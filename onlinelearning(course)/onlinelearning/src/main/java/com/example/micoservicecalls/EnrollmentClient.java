package com.example.micoservicecalls;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Enrollment-Module") // This should match the Eureka service name
public interface EnrollmentClient {

    @GetMapping("/enrollment/count/{courseId}")  
    int getEnrollmentCount(@PathVariable("courseId") String courseId);
}
