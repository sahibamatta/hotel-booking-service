package com.hotelbooking.configuration;


public enum ErrorCodes {

	BAD_REQUEST("The Requested API Params (names and types) do not match with provided params"), 
	INTERNAL_SERVER_ERROR("The server is currently unable to serve the API Request"), 
	REQUESTED_RESOURCE_NOT_FOUND("The server could not find the resource you are looking for. Check API URL and Params");

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	ErrorCodes(String message) {
		this.message = message;
	}
}