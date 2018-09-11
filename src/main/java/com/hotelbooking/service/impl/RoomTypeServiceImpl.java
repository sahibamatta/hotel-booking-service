package com.hotelbooking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbooking.constants.Constants;
import com.hotelbooking.dto.DatewiseInventoryResponseDto;
import com.hotelbooking.dto.RoomDetailsResponseDto;
import com.hotelbooking.entity.DateWiseAvailableInventoryEntity;
import com.hotelbooking.entity.RoomtypeDetailsEntity;
import com.hotelbooking.exception.HotelBookingException;
import com.hotelbooking.repository.DateWiseAvailableInventoryRepository;
import com.hotelbooking.repository.RoomDetailsRepository;
import com.hotelbooking.service.RoomTypeService;
import com.hotelbooking.util.HotelBookingUtil;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class RoomTypeServiceImpl implements RoomTypeService {

	@Autowired
	private RoomDetailsRepository roomDetailsRepository;

	@Autowired
	private DateWiseAvailableInventoryRepository dateWiseAvailableInventoryRepository;

	@Override
	public List<RoomDetailsResponseDto> getRoomTypeData() throws HotelBookingException {
		log.info("in getRoomTypeData");
		List<RoomtypeDetailsEntity> roomtypeDetailsEntities = new ArrayList<>();
		roomDetailsRepository.findAll().forEach(roomtypeDetailsEntities::add);

		List<RoomDetailsResponseDto> roomDetailsResponseDtos = new ArrayList<>();

		if(roomtypeDetailsEntities.isEmpty())
			throw new HotelBookingException(Constants.ROOMTYPE_ERROR_MESSAGE);

		for(RoomtypeDetailsEntity roomtypeDetailsEntity :roomtypeDetailsEntities) {
			roomDetailsResponseDtos.add(HotelBookingUtil.roomTypeEntityToDto(roomtypeDetailsEntity));
		}

		return roomDetailsResponseDtos;
	}

	@Override
	public List<DatewiseInventoryResponseDto> getTimeSlotsBasedOnRoomType(String type) throws HotelBookingException {
		log.info("getTimeSlotsBasedOnRoomType in service is ::"+type);

		RoomtypeDetailsEntity roomtypeDetailsEntity = getRoomIdFromName(type);
		log.info("roomtypeDetailsEntity after getRoomIdFromName is:: "+roomtypeDetailsEntity);
		return getInventoryDetailsBasedOnRoomType(roomtypeDetailsEntity);
	}

	private List<DatewiseInventoryResponseDto> getInventoryDetailsBasedOnRoomType(RoomtypeDetailsEntity roomtypeDetailsEntity) 
			throws HotelBookingException{

		log.info("in getInventoryDetailsBasedOnRoomType id is:"+roomtypeDetailsEntity.getId());
		List<DateWiseAvailableInventoryEntity>dateWiseAvailableInventoryEntities=
				dateWiseAvailableInventoryRepository.findByRoomTypeIdOrderByDate(roomtypeDetailsEntity.getId());

		dateWiseAvailableInventoryEntities=dateWiseAvailableInventoryEntities.stream().filter(entity -> 
		entity.getAvailableInventory()!=0 && entity.getAvailableInventory()>entity.getBookingProgressCount()).collect(Collectors.toList());

		if(dateWiseAvailableInventoryEntities.isEmpty())
			throw new HotelBookingException(Constants.NO_INVENTORY_LEFT);

		List<DatewiseInventoryResponseDto> datewiseInventoryResponseDtos = new ArrayList<>();

		for(DateWiseAvailableInventoryEntity dateWiseAvailableInventoryEntity : dateWiseAvailableInventoryEntities) {
			datewiseInventoryResponseDtos.add(HotelBookingUtil.dateWiseInventoryEntityToDto(dateWiseAvailableInventoryEntity));
		}

		return datewiseInventoryResponseDtos;
	}

	private RoomtypeDetailsEntity getRoomIdFromName(String type) throws HotelBookingException {
		log.info("in getRoomIdFromName");
		RoomtypeDetailsEntity roomtypeDetailsEntity=roomDetailsRepository.findByName(type);

		if(Objects.isNull(roomtypeDetailsEntity))
			throw new HotelBookingException(Constants.ROOMTYPE_NOT_EXIST);
		return roomtypeDetailsEntity;
	}


}
