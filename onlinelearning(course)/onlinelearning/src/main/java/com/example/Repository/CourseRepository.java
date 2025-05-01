package com.example.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    
    List<Course> findByPriceBetween(float minPrice, float maxPrice);
    
    List<Course> findByCourseNameIgnoreCaseContaining(String courseName);
    
    List<Course> findByCategoryAndRatingGreaterThanEqualAndDifficulty(String category, float rating, String difficulty);

	List<Course> findByCategoryAndRatingAndDifficulty(String category, float rating, String difficulty);

	List<Course> findByCourseNameContaining(String courseName);
}
