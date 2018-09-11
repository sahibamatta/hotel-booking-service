package com.hotelbooking.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Test;

import com.hotelbooking.dto.DatewiseInventoryResponseDto;
import com.hotelbooking.dto.RoomDetailsResponseDto;
import com.hotelbooking.entity.DateWiseAvailableInventoryEntity;
import com.hotelbooking.entity.RoomtypeDetailsEntity;


public class HotelBookingUtilTest {

	@Test
	public void roomTypeEntityWillProduceDto(){

		RoomtypeDetailsEntity roomtypeDetailsEntity = new RoomtypeDetailsEntity("Deluxe",new Integer(4),new Double(2000));
		RoomDetailsResponseDto roomDetailsResponseDtoExpected = HotelBookingUtil.roomTypeEntityToDto(roomtypeDetailsEntity);
		RoomDetailsResponseDto roomDetailsResponseDtoActual = new RoomDetailsResponseDto("Deluxe",new Integer(4),new Double(2000));

		System.out.println("roomDetailsResponseDtoExpected::"+roomDetailsResponseDtoExpected
				+"roomDetailsResponseDtoActual::"+roomDetailsResponseDtoActual);
		assertThat(roomDetailsResponseDtoExpected).isNotNull();
		assertThat(roomDetailsResponseDtoExpected).isEqualToComparingFieldByField(roomDetailsResponseDtoActual);

	}

	@Test
	public void dateWiseInventoryEntityWillProduceDto() {

		LocalDate localDate = LocalDate.of(2018,9,11);

		DateWiseAvailableInventoryEntity dateWiseAvailableInventoryEntity = 
				new DateWiseAvailableInventoryEntity(new Long(100), localDate,10,0);
		DatewiseInventoryResponseDto datewiseInventoryResponseDtoExpected = 
				HotelBookingUtil.dateWiseInventoryEntityToDto(dateWiseAvailableInventoryEntity);

		DatewiseInventoryResponseDto datewiseInventoryResponseDtoActual =
				new DatewiseInventoryResponseDto(localDate,10); 

		assertThat(datewiseInventoryResponseDtoExpected).isNotNull();
		assertThat(datewiseInventoryResponseDtoExpected).isEqualToComparingFieldByField(datewiseInventoryResponseDtoActual);



	}



}
