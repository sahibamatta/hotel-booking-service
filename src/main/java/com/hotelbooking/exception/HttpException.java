package com.hotelbooking.exception;


public class HttpException extends Exception {

	private static final long serialVersionUID = 6466676864511263741L;
	private String message;
	private Throwable error;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getError() {
		return error;
	}

	public void setError(Throwable error) {
		this.error = error;
	}

	public HttpException() {
		super();
	}

	public HttpException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
		this.error = cause;
	}

	public HttpException(String message) {
		super(message);
		this.message = message;
	}

	public HttpException(Throwable cause) {
		super(cause);
		this.error = cause;
	}
}