package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

import com.example.entity.Progress;
import com.example.exception.UserProgressNotFoundException;
import com.example.repository.UserProgressRepository;

@Service
public class UserProgressServiceImp implements UserProgressService {
    
    @Autowired
    private UserProgressRepository userProgressRepository;

   
    @Override
    public Progress getProgressByUserId(int id)throws UserProgressNotFoundException {
        return userProgressRepository.findById(id)
                .orElseThrow(() -> new UserProgressNotFoundException("No progress record found for User ID: " + id));
    }
    
    @Override
    public List<Progress> getProgressByCourse(String courseId) throws UserProgressNotFoundException {
        List<Progress> progressList = userProgressRepository.findByCourseId(courseId);

        if (progressList.isEmpty()) {
            throw new UserProgressNotFoundException("No progress records found for Course ID: " + courseId);
        }

        return progressList;
    }
  

    @Override
    public List<Progress> getProgressByQuiz(String quizId) throws UserProgressNotFoundException {
        List<Progress> progressList = userProgressRepository.findByQuizId(quizId);

        if (progressList.isEmpty()) {
            throw new UserProgressNotFoundException("No progress records found for Quiz ID: " + quizId);
        }

        return progressList;
}
    
    @Override
    public Progress createProgress(Progress progress) {
        return userProgressRepository.save(progress);
    }
    
    
    @Override
    public Progress updateProgress(int id, Progress progress) throws UserProgressNotFoundException {
        Progress existingprogress = userProgressRepository.findById(id)
                .orElseThrow(() -> new UserProgressNotFoundException("Progress record not found for ID: " + id));

        existingprogress.setProgressPercentage(progress.getProgressPercentage());
        existingprogress.setCompletionStatus(progress.getCompletionStatus());
        existingprogress.setCourseId(progress.getCourseId());
        existingprogress.setQuizId(progress.getQuizId());
       
        
        return userProgressRepository.save(progress);
    }
    
    @Override
    public void deleteProgress(int id) {
        if (userProgressRepository.existsById(id)) {
            userProgressRepository.deleteById(id);
        } else {
            throw new RuntimeException("Progress not found with id: " + id);
        }
    }
 
    @Override
    public List<Progress> getCompletedUsers() throws UserProgressNotFoundException {
        List<Progress> progressList = userProgressRepository.findAll();
        
        if (progressList.isEmpty()) {
            throw new UserProgressNotFoundException("No Progress Available");
        }

        List<Progress> completedUsers = progressList.stream()
                .filter(progress -> "Completed".equals(progress.getCompletionStatus())) 
                .collect(Collectors.toList());

        return completedUsers;
    }
    
    @Override
    public List<Progress> getAllProgress() throws UserProgressNotFoundException {
        List<Progress> progressList = userProgressRepository.findAll();
        
        if (progressList.isEmpty()) {
            throw new UserProgressNotFoundException("No progress records available.");
        }

        return progressList;
    }
    
}
