package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Course;
import com.example.entity.Coursecontent;
import com.example.exceptions.CourseContentNotFoundException;
import com.example.exceptions.CourseNotFoundException;
import com.example.Repository.CourseRepository;
import com.example.Repository.CourseContentRepository;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentRepository courseContentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Coursecontent addContentToCourse(String courseId, Coursecontent content) throws CourseContentNotFoundException, CourseNotFoundException {
        Course existingCourse = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course ID not found: " + courseId));
        content.setCourseObj(existingCourse);
        return courseContentRepository.save(content);
    }
    @Override
    public Coursecontent updateContent(String contentId, Coursecontent content) throws CourseContentNotFoundException {
        Coursecontent existingContent = courseContentRepository.findById(contentId)
                .orElseThrow(() -> new CourseContentNotFoundException("Content ID not found: " + contentId));

        existingContent.setContentType(content.getContentType());
        existingContent.setUserId(content.getUserId());
        existingContent.setRating(content.getRating());
        existingContent.setDescription(content.getDescription());
        existingContent.setIssueDescription(content.getIssueDescription());
        existingContent.setFeedback(content.getFeedback());
        existingContent.setCompleted(content.getCompleted());

        return courseContentRepository.save(existingContent);
    }

    @Override
    public void deleteContent(String contentId) throws CourseContentNotFoundException {
        Coursecontent existingContent = courseContentRepository.findById(contentId)
                .orElseThrow(() -> new CourseContentNotFoundException("Content ID not found: " + contentId));

        courseContentRepository.delete(existingContent);
    }
    @Override
    public void markContentAsCompleted(String userId, String contentId,String Completed) throws CourseContentNotFoundException {
        Coursecontent existingContent = courseContentRepository.findById(contentId)
                .orElseThrow(() -> new CourseContentNotFoundException("Content ID not found: " + contentId));
        existingContent.setCompleted(Completed);
        courseContentRepository.save(existingContent);
    }

    @Override
    public void reportContentIssue(String userId, String contentId, String issueDescription) throws CourseContentNotFoundException {
        Coursecontent existingContent = courseContentRepository.findById(contentId)
                .orElseThrow(() -> new CourseContentNotFoundException("Content ID not found: " + contentId));

        existingContent.setIssueDescription(issueDescription);
        courseContentRepository.save(existingContent);
    }
    
    public List<Coursecontent> getAllcontent(String courseId)throws CourseNotFoundException{
    	Course existing = courseRepository.findById(courseId).orElseThrow(()-> new CourseNotFoundException("Course Id not found: "+courseId));
    	return existing.getCoursecontentList();
    }
}
