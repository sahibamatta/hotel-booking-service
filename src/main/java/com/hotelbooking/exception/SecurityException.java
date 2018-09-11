package com.hotelbooking.exception;

import lombok.Getter;

@Getter
public class SecurityException extends RuntimeException {

	private static final long serialVersionUID = -4137212821085031639L;

	private int statusCode = 401; 
	
	public SecurityException() {
		
	}
	
	public SecurityException(String message) {
		super(message);
	}
	
	public SecurityException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}
	
}
