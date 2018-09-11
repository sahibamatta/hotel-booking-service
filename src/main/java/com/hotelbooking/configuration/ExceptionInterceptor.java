package com.hotelbooking.configuration;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hotelbooking.common.dto.ResponseDto;
import com.hotelbooking.exception.ApiValidationException;
import com.hotelbooking.exception.CasaException;
import com.hotelbooking.exception.HotelBookingException;
import com.hotelbooking.exception.HttpException;
import com.hotelbooking.util.ResponseUtil;

import lombok.extern.log4j.Log4j;


@RestControllerAdvice
@Log4j
public class ExceptionInterceptor {

	@ExceptionHandler(SecurityException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public <T, E> ResponseDto<T, E> handleSecurityException(SecurityException e) {

		String exceptionId = UUID.randomUUID().toString();
		log.error("Got SecurityException for exceptionId: " + exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(CasaException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public <T, E> ResponseDto<T, E> handleCasaException(CasaException e) {

		String exceptionId = UUID.randomUUID().toString();
		log.error("Got CasaException for exceptionId: " + exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(ApiValidationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody <T, E> ResponseDto<T, E> handleApiValidationException(ApiValidationException e) {

		String exceptionId = UUID.randomUUID().toString();
		log.error("Got ApiValidationException for exceptionId: " + exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(HttpException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody <T, E> ResponseDto<T, E> handleCasaHttpException(HttpException e) {

		String exceptionId =  UUID.randomUUID().toString();
		log.error("Got CasaHttpException for exceptionId: " + exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody <T, E> ResponseDto<T, E> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

		String exceptionId = UUID.randomUUID().toString();
		log.error("Got MethodArgumentNotValidException for exceptionId:" + exceptionId, e);

		return ResponseDto.failure(ResponseUtil.generateValidationMessage(e.getBindingResult()), exceptionId);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody <T, E> ResponseDto<T, E> handleException(Exception ex) {

		String exceptionId = UUID.randomUUID().toString();
		log.error("Got un-handled exception for exceptionId: " + exceptionId, ex);

		return ResponseDto.failure(ErrorCodes.INTERNAL_SERVER_ERROR.getMessage(), exceptionId);
	}

	@ExceptionHandler(HotelBookingException.class)
	public <T, E> ResponseDto<T, E> handleCouponException(HotelBookingException e) {

		String exceptionId = UUID.randomUUID().toString();
		log.error("Got hotelbookingexcpetion for exceptionId: " + exceptionId, e);

		return ResponseDto.failure(e.getDescription(), exceptionId);
	}


}