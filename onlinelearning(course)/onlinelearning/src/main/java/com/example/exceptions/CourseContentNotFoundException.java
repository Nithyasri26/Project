package com.example.exceptions;

public class CourseContentNotFoundException extends RuntimeException{
	public CourseContentNotFoundException(String message) {
		super(message);
	}
}
