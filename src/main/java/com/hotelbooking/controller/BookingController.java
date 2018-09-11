package com.hotelbooking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbooking.common.dto.ResponseDto;
import com.hotelbooking.constants.Constants;
import com.hotelbooking.dto.BookingRequestDto;
import com.hotelbooking.dto.BookingResponseDto;
import com.hotelbooking.dto.RoomTypeSelectionRequestDto;
import com.hotelbooking.dto.RoomTypeSelectionResponseDto;
import com.hotelbooking.exception.HotelBookingException;
import com.hotelbooking.service.BookingService;

import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;

@Api("Booking of Rooms")
@RestController
@RequestMapping("booking")
@Log4j
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping(value="/select/room")

	public ResponseDto<RoomTypeSelectionResponseDto, Object> selectRoomType(
			@RequestBody @Valid RoomTypeSelectionRequestDto roomTypeSelectionRequestDto) throws HotelBookingException{
		log.info("In selectRoomType");
		return ResponseDto.success(Constants.ROOMTYPE_SELECTION_SUCCESS_MESSAGE, bookingService
				.selectRoomTypeAndIncrementBookingProgressCount(roomTypeSelectionRequestDto));
	}

	@PostMapping(value="/makeBooking")
	public ResponseDto<BookingResponseDto, Object> 
	makeBooking(@RequestBody @Valid BookingRequestDto bookingRequestDto) throws HotelBookingException{
		log.info("In makeBooking ::"+bookingRequestDto);
		return ResponseDto.success(Constants.BOOKING_SUCCESS, bookingService.makeBooking(bookingRequestDto));
	}
}
