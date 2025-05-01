package com.example.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.CourseRepository;
import com.example.entity.Course;
import com.example.exceptions.CourseNotFoundException;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createCourse(Course courseObj) {
        return courseRepository.save(courseObj);
    }

    @Override
    public Course updateCourse(String courseId, Course updatedCourse) throws CourseNotFoundException {
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        existingCourse.setCourseName(updatedCourse.getCourseName());
        existingCourse.setPrice(updatedCourse.getPrice());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setDuration(updatedCourse.getDuration());
        existingCourse.setCategory(updatedCourse.getCategory());
        existingCourse.setRating(updatedCourse.getRating());
        existingCourse.setDifficulty(updatedCourse.getDifficulty());

        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(String courseId) throws CourseNotFoundException {
        courseRepository.findById(courseId).ifPresentOrElse(
            courseRepository::delete,
            () -> { throw new CourseNotFoundException("Course Not found: " + courseId); }
        );
    }

    @Override
    public List<Course> searchCourses(String category, float rating, String difficulty) {
        return courseRepository.findByCategoryAndRatingAndDifficulty(category, rating, difficulty);
    }

    @Override
    public List<Course> searchCourseByCourseName(String courseName) throws CourseNotFoundException {
        List<Course> courses = courseRepository.findByCourseNameContaining(courseName);
        if (courses.isEmpty()) {
            throw new CourseNotFoundException("No courses found with name: " + courseName);
        }
        return courses;
    }

    @Override
    public List<Course> filterCoursesByPriceRange(float minPrice, float maxPrice) throws CourseNotFoundException {
        List<Course> courses = courseRepository.findByPriceBetween(minPrice, maxPrice);
        if (courses.isEmpty()) {
            throw new CourseNotFoundException("No courses found in the price range: " + minPrice + " - " + maxPrice);
        }
        return courses;
    }

    @Override
    public Course searchCourseById(String courseId) throws CourseNotFoundException {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + courseId));
    }

    @Override
    public List<Course> getAllCourses() throws CourseNotFoundException {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            throw new CourseNotFoundException("No courses available");
        }
        return courses;
    }

	@Override
	public List<Course> searchCoursebycourseName(String courseName) {
		// TODO Auto-generated method stub
		return null;
	}
}
