package com.hotelbooking.exception;


public class CasaException extends Exception {

	private static final long serialVersionUID = 54730819L;

	private String code;

	public CasaException() {
		super();
	}

	public CasaException(String message) {
		super(message);
	}

	public CasaException(Throwable cause) {
		super(cause);
	}

	public CasaException(String message, String code) {
		super(message);
		this.code = code;
	}

	public CasaException(String message, Throwable cause) {
		super(message, cause);
	}

	public CasaException(String message, String code, Throwable error) {
		super(message, error);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}