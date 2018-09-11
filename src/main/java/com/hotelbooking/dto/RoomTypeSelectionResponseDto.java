package com.hotelbooking.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoomTypeSelectionResponseDto {
	
	private String roomType;
	private LocalDate checkin;
	private LocalDate checkout;
	private Double totalPriceToBePaid;
}

