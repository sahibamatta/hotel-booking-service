package com.hotelbooking.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.hotelbooking.constants.Constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoomTypeSelectionRequestDto {

	@NotNull(message= "Roomtype"+Constants.BLANK_CHECK)
	private String roomType;
	@NotNull(message= "Checkin"+Constants.BLANK_CHECK)
	private LocalDate checkin;
	@NotNull(message= "Checkout"+Constants.BLANK_CHECK)
	private LocalDate checkout;
	@NotNull(message= "Price Per Night"+Constants.BLANK_CHECK)
	private Double pricePerNight;
}
