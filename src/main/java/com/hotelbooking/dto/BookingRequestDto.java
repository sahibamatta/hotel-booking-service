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
public class BookingRequestDto {

	@NotNull(message ="RoomType"+Constants.BLANK_CHECK)
	private String roomType;
	@NotNull(message="Price Per Night"+Constants.BLANK_CHECK)
	private Double pricePerNight;
	@NotNull(message="Checkin"+Constants.BLANK_CHECK)
	private LocalDate checkin;
	@NotNull(message="Checkout"+Constants.BLANK_CHECK)
	private LocalDate checkout;
	@NotNull(message="EmailId"+Constants.BLANK_CHECK)
	private String emailId;
	@NotNull(message="Phone Number"+Constants.BLANK_CHECK)
	private String phoneNumber;
}

