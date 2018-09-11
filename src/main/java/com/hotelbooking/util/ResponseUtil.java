package com.hotelbooking.util;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hotelbooking.constants.Constants;
import com.hotelbooking.dto.ResponseDto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseUtil {

	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static ResponseDto generateResponse(String status, String message, Object data) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(status);
		responseDto.setMessage(message);
		responseDto.setData(gson.toJson(data));
		return responseDto;
	}

	public static ResponseDto generateResponse(String status, String message) {
		return generateResponse(status, message, null);
	}

	public static ResponseDto generateResponseFromData(Object data) {
		return generateResponse(Constants.RESPONSE_STATUS_SUCCESS, Constants.RESPONSE_STATUS_SUCCESS_MESSAGE, data);
	}

	public static ResponseDto generateResponseFromException(Exception e) {
		return generateResponse(Constants.RESPONSE_STATUS_ERROR_MESSAGE, e.fillInStackTrace().toString(), null);
	}

	public static String generateValidationMessage(BindingResult result) {
		StringBuilder builder = new StringBuilder();
		String delimiter = "";

		List<ObjectError> fieldErrors = result.getGlobalErrors();
		for (ObjectError s : fieldErrors) {
			builder.append(delimiter);
			delimiter = " | ";
			builder.append(s.getDefaultMessage());
		}

		List<FieldError> fieldErrors1 = result.getFieldErrors();
		for (FieldError s : fieldErrors1) {
			builder.append(delimiter);
			delimiter = " | ";
			builder.append(s.getDefaultMessage());
		}
		return builder.toString();
	}

}