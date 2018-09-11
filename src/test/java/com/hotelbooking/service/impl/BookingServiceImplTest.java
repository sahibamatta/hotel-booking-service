package com.hotelbooking.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class BookingServiceImplTest {
	
	
	@Test
	public void getDatesWillProduceDatesBetweenTwoDates(){
		LocalDate startDate= LocalDate.of(2018,9,11);
		LocalDate endDate= LocalDate.of(2018,9,14);
		LocalDate date1Between = LocalDate.of(2018,9,12);
		LocalDate date2Between = LocalDate.of(2018,9,13);
		List<LocalDate> dateRangeActual = new ArrayList<>(Arrays.asList
				(startDate,date1Between,date2Between,endDate));
		
		List<LocalDate> dateRangeExpected = BookingServiceImpl.getDatesBetween(startDate, endDate);
		
		
		assertThat(dateRangeExpected).isNotNull().isNotEmpty();
		//Date lists size compare
		assertThat(dateRangeExpected.size()).isEqualTo(dateRangeActual.size());
		//Boundary conditions check
		assertThat(dateRangeExpected.get(0)).isEqualTo(startDate);
		assertThat(dateRangeExpected.get(dateRangeExpected.size()-1)).isEqualTo(endDate);
		
		//Entire date list compare
		assertThat(dateRangeExpected).isEqualTo(dateRangeActual);
	}


}
