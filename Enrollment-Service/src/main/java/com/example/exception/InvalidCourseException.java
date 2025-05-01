package com.example.exception;

public class InvalidCourseException extends RuntimeException{
	
	public InvalidCourseException (String msg) {
		super(msg);
	}

}
