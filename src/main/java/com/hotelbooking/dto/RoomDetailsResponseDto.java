package com.hotelbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class RoomDetailsResponseDto {

	String roomType;
	Integer rating;
	Double price;

}
