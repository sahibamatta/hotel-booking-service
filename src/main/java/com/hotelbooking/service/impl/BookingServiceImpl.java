package com.hotelbooking.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbooking.constants.Constants;
import com.hotelbooking.dto.BookingRequestDto;
import com.hotelbooking.dto.BookingResponseDto;
import com.hotelbooking.dto.RoomTypeSelectionRequestDto;
import com.hotelbooking.dto.RoomTypeSelectionResponseDto;
import com.hotelbooking.entity.DateWiseAvailableInventoryEntity;
import com.hotelbooking.exception.HotelBookingException;
import com.hotelbooking.repository.BookingDetailsRepository;
import com.hotelbooking.repository.DateWiseAvailableInventoryRepository;
import com.hotelbooking.repository.RoomDetailsRepository;
import com.hotelbooking.service.BookingService;
import com.hotelbooking.util.HotelBookingUtil;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class BookingServiceImpl implements BookingService{

	@Autowired
	private DateWiseAvailableInventoryRepository dateWiseAvailableInventoryRepository;

	@Autowired
	private RoomDetailsRepository roomDetailsReposiotry;

	@Autowired
	private BookingDetailsRepository bookingDetailsRepository;

	@Override
	public RoomTypeSelectionResponseDto selectRoomTypeAndIncrementBookingProgressCount
	(RoomTypeSelectionRequestDto roomTypeSelectionRequestDto) throws HotelBookingException {

		log.info("selectRoomTypeAndIncrementBookingProgressCount request is::"+roomTypeSelectionRequestDto);
		validateCheckinCheckout(roomTypeSelectionRequestDto.getCheckin(),roomTypeSelectionRequestDto.getCheckout());
		List <LocalDate> dateRanges=getDatesBetween(roomTypeSelectionRequestDto.getCheckin(),roomTypeSelectionRequestDto.getCheckout());

		checkIfRoomAvailableBetweenGivenDates(dateRanges,getRoomTypeIdByName(roomTypeSelectionRequestDto.getRoomType()));

		incrementBookingProgressCount(dateRanges,getRoomTypeIdByName(roomTypeSelectionRequestDto.getRoomType()));

		return getRoomTypeSelectionResponseDto(roomTypeSelectionRequestDto,dateRanges.size());
	}

	public Long getRoomTypeIdByName(String name) throws HotelBookingException {
		log.info("getRoomTypeIdByName name is::"+name);
		if(Objects.isNull(roomDetailsReposiotry.findByName(name)))
			throw new HotelBookingException(Constants.INVALID_ROOMTYPE);

		return roomDetailsReposiotry.findByName(name).getId();

	}

	public static List<LocalDate> getDatesBetween(LocalDate startDate , LocalDate endDate){
		log.info("getDatesBetween");	
		List<LocalDate> dates = new ArrayList<> ();
		for (LocalDate d = startDate; !d.isAfter(endDate); d = d.plusDays(1)) {
			dates.add(d);
		}
		log.info("dates bewteen are::"+dates);
		return dates;
	}

	private static void validateCheckinCheckout(LocalDate startDate , LocalDate endDate) throws HotelBookingException 
	{

		if(startDate.isBefore(LocalDate.of(2018,9,12)))
			throw new HotelBookingException(Constants.CHECKIN_VALID);

		if(endDate.isAfter(LocalDate.of(2018, 9,19)))
			throw new HotelBookingException(Constants.CHECKOUT_VALID);
	}

	private void checkIfRoomAvailableBetweenGivenDates(List<LocalDate> dateRanges,Long roomTypeId )
			throws HotelBookingException {
		log.info("checkIfRoomAvailableBetweenGivenDates::");

		for(LocalDate date : dateRanges) {
			DateWiseAvailableInventoryEntity dateWiseAvailableInventoryEntity=
					dateWiseAvailableInventoryRepository.findByRoomTypeIdAndDate
					(roomTypeId,date);

			if(dateWiseAvailableInventoryEntity.getAvailableInventory()==0
					||dateWiseAvailableInventoryEntity.getAvailableInventory()==dateWiseAvailableInventoryEntity.getBookingProgressCount())
				throw new HotelBookingException("Cannot select this room type no inventory avalilable for :"+date);

		}
	}

	private void incrementBookingProgressCount(List<LocalDate> dateRanges,Long roomTypeId ) {
		log.info("in incrementBookingProgressCount");
		for(LocalDate date : dateRanges) {
			DateWiseAvailableInventoryEntity dateWiseAvailableInventoryEntity=
					dateWiseAvailableInventoryRepository.findByRoomTypeIdAndDate(roomTypeId, date);
			dateWiseAvailableInventoryEntity.setBookingProgressCount
			(dateWiseAvailableInventoryEntity.getBookingProgressCount()+1);
			dateWiseAvailableInventoryRepository.save(dateWiseAvailableInventoryEntity);
		}
	}

	private RoomTypeSelectionResponseDto getRoomTypeSelectionResponseDto(RoomTypeSelectionRequestDto
			roomTypeSelectionRequestDto , int nights) {
		log.info("in getRoomTypeSelectionResponseDto");

		return new RoomTypeSelectionResponseDto(roomTypeSelectionRequestDto.getRoomType(),
				roomTypeSelectionRequestDto.getCheckin(), roomTypeSelectionRequestDto.getCheckout(), 
				roomTypeSelectionRequestDto.getPricePerNight()*nights);
	}

	@Override
	public BookingResponseDto makeBooking(BookingRequestDto bookingRequestDto) throws HotelBookingException {
		log.info("in makeBooking request is::"+bookingRequestDto);
		List<LocalDate> dateRanges=getDatesBetween(bookingRequestDto.getCheckin(), bookingRequestDto.getCheckout());

		for(LocalDate date : dateRanges) {
			DateWiseAvailableInventoryEntity dateWiseAvailableInventoryEntity=
					dateWiseAvailableInventoryRepository.findByRoomTypeIdAndDate(getRoomTypeIdByName(bookingRequestDto.getRoomType()), date);
			dateWiseAvailableInventoryEntity.setBookingProgressCount(dateWiseAvailableInventoryEntity.getBookingProgressCount()-1);
			dateWiseAvailableInventoryEntity.setAvailableInventory(dateWiseAvailableInventoryEntity.getAvailableInventory()-1);

			dateWiseAvailableInventoryRepository.save(dateWiseAvailableInventoryEntity);
		}

		return getBookingResponseAndPersistInDb(bookingRequestDto, dateRanges.size());

	}
	private BookingResponseDto getBookingResponseAndPersistInDb(BookingRequestDto bookingRequestDto,int nights) {	
		BookingResponseDto bookingResponseDto=new BookingResponseDto(RandomStringUtils.randomAlphanumeric(6).toUpperCase(), 
				bookingRequestDto.getRoomType(), LocalDate.now(), bookingRequestDto.getCheckin(),
				bookingRequestDto.getCheckout(),bookingRequestDto.getPricePerNight()*nights);
		bookingDetailsRepository.save(HotelBookingUtil.bookingDetailsDtoToEntity(bookingResponseDto));

		return bookingResponseDto;

	}


}
