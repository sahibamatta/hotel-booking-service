package com.hotelbooking.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DatewiseInventoryResponseDto {

	LocalDate date ;
	Integer availableInventory;

}
