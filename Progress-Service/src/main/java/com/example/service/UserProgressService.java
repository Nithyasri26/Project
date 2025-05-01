package com.example.service;

import java.util.List;



import com.example.entity.Progress;
import com.example.exception.UserProgressNotFoundException;

public interface UserProgressService {
	
	 Progress getProgressByUserId(int id) throws UserProgressNotFoundException;

	List<Progress> getProgressByCourse(String courseId) throws UserProgressNotFoundException;

    List<Progress> getProgressByQuiz(String quizId) throws UserProgressNotFoundException;

    Progress createProgress(Progress progress);
    
	Progress updateProgress(int id, Progress progress) throws UserProgressNotFoundException;
	
	void deleteProgress(int id);
	
	List<Progress> getAllProgress() throws UserProgressNotFoundException;
	
	List<Progress> getCompletedUsers() throws UserProgressNotFoundException;
	
}
