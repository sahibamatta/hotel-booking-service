package com.hotelbooking.util;

import java.time.LocalDate;

import com.hotelbooking.dto.BookingRequestDto;
import com.hotelbooking.dto.BookingResponseDto;
import com.hotelbooking.dto.DatewiseInventoryResponseDto;
import com.hotelbooking.dto.RoomDetailsResponseDto;
import com.hotelbooking.entity.BookingDetailsEntity;
import com.hotelbooking.entity.DateWiseAvailableInventoryEntity;
import com.hotelbooking.entity.RoomtypeDetailsEntity;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j;

@UtilityClass
@Log4j
public class HotelBookingUtil {

	public RoomDetailsResponseDto roomTypeEntityToDto(RoomtypeDetailsEntity roomtypeDetailsEntity){
		log.info("roomTypeEntityToDto entity is:"+roomtypeDetailsEntity);
		return new RoomDetailsResponseDto
				(roomtypeDetailsEntity.getName() ,roomtypeDetailsEntity.getRating(),
						roomtypeDetailsEntity.getPrice());


	}

	public DatewiseInventoryResponseDto dateWiseInventoryEntityToDto
	(DateWiseAvailableInventoryEntity dateWiseAvailableInventoryEntity){
		log.info("dateWiseInventoryEntityToDto entity is:"+dateWiseAvailableInventoryEntity);
		return new DatewiseInventoryResponseDto(dateWiseAvailableInventoryEntity.getDate(),
				dateWiseAvailableInventoryEntity.getAvailableInventory());



	}
	
	public BookingDetailsEntity bookingDetailsDtoToEntity(BookingResponseDto bookingResponseDto){
		log.info("BookingDetailsEntity is:"+bookingResponseDto);
		return new BookingDetailsEntity(bookingResponseDto.getBookingId(),
				bookingResponseDto.getRoomType(), LocalDate.now(), bookingResponseDto.getCheckin(),
				bookingResponseDto.getCheckout(),bookingResponseDto.getTotalPrice());



	}

}
