package com.hotelbooking.service;

import java.util.List;

import com.hotelbooking.dto.DatewiseInventoryResponseDto;
import com.hotelbooking.dto.RoomDetailsResponseDto;
import com.hotelbooking.exception.HotelBookingException;

public interface RoomTypeService {

	public List<RoomDetailsResponseDto> getRoomTypeData()  throws HotelBookingException ;
	public List<DatewiseInventoryResponseDto> getTimeSlotsBasedOnRoomType(String type) throws HotelBookingException ;

}
