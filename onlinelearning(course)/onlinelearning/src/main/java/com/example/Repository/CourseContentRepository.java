package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Course;
import com.example.entity.Coursecontent;

@Repository
public interface CourseContentRepository extends JpaRepository<Coursecontent, String> {
    List<Coursecontent> findBookmarkedContentByUserId(String userId);

}