package com.hotelbooking.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookingResponseDto {

	private String bookingId;
	private String roomType;
	private LocalDate bookingDate;
	private LocalDate checkin;
	private LocalDate checkout;
	private Double totalPrice;
}

