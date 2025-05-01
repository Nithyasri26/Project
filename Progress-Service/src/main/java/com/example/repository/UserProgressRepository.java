package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Progress;


@Repository
public interface UserProgressRepository extends JpaRepository<Progress, Integer>{


	List<Progress> findByCourseId(String courseId);

	List<Progress> findByQuizId(String quizId);

	

	

	
	
	
	

}
