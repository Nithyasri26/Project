package com.example.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

	Optional<Enrollment> findByMobileNumber(Long mobileNumber);

	Optional<Enrollment> findById(int studentId);
	
	
	
}

