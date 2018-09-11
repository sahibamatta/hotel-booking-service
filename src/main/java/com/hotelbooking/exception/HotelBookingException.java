package com.hotelbooking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class HotelBookingException extends Exception {

	private static final long serialVersionUID = -3152859515516964839L;
	private final String description;

}
