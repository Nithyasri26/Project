package com.example.Service;

import java.util.List;

import com.example.entity.Course;
import com.example.entity.Coursecontent;
import com.example.exceptions.CourseContentNotFoundException;
import com.example.exceptions.CourseNotFoundException;

public interface CourseContentService {

	Coursecontent addContentToCourse(String courseId, Coursecontent content);

	Coursecontent updateContent(String contentId, Coursecontent content);

	void deleteContent(String contentId);

	//void markContentAsCompleted(String userId, String contentId);

	void reportContentIssue(String userId, String contentId, String issueDescription);
	
	List<Coursecontent> getAllcontent(String courseId);

	void markContentAsCompleted(String userId, String contentId, String Completed)
			throws CourseContentNotFoundException;
}
