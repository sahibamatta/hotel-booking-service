package com.hotelbooking.service;

import com.hotelbooking.dto.BookingRequestDto;
import com.hotelbooking.dto.BookingResponseDto;
import com.hotelbooking.dto.RoomTypeSelectionRequestDto;
import com.hotelbooking.dto.RoomTypeSelectionResponseDto;
import com.hotelbooking.exception.HotelBookingException;

public interface BookingService {
	
	public BookingResponseDto makeBooking(BookingRequestDto bookingRequestDto) throws HotelBookingException;
	
	public RoomTypeSelectionResponseDto selectRoomTypeAndIncrementBookingProgressCount
	(RoomTypeSelectionRequestDto roomTypeSelectionRequestDto) throws HotelBookingException;

}
