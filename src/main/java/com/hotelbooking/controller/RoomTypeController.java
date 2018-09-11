package com.hotelbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbooking.common.dto.ResponseDto;
import com.hotelbooking.constants.Constants;
import com.hotelbooking.dto.DatewiseInventoryResponseDto;
import com.hotelbooking.dto.RoomDetailsResponseDto;
import com.hotelbooking.exception.HotelBookingException;
import com.hotelbooking.service.RoomTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j;

@Api("List Of Room Types")
@RestController
@RequestMapping("room")
@Log4j
public class RoomTypeController {

	@Autowired
	private RoomTypeService roomTypeService;

	@GetMapping(value="/types")
	@ApiParam(allowableValues="deluxe,suite")
	public ResponseDto<List<RoomDetailsResponseDto>, Object> getRoomDetails() 
			throws HotelBookingException{
		log.info("In getRoomDetails");
		return ResponseDto.success(Constants.ROOMTYPE_SUCCESS_MESSAGE, roomTypeService.getRoomTypeData());
	}

	@GetMapping(value="/{type}")
	public ResponseDto<List<DatewiseInventoryResponseDto>, Object> 
	getAvailableInventoryForARoomType(@PathVariable String type ) throws HotelBookingException{
		log.info("In getAvailableInventoryForARoomType ::"+type);
		return ResponseDto.success(Constants.TIME_SLOT_ROOM_TYPE_SUCCESS, 
				roomTypeService.getTimeSlotsBasedOnRoomType(type));
	}
}
