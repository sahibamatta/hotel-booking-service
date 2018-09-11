package com.hotelbooking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hotelbooking.entity.DateWiseAvailableInventoryEntity;

public interface DateWiseAvailableInventoryRepository extends CrudRepository
<DateWiseAvailableInventoryEntity, Long>{

	List<DateWiseAvailableInventoryEntity> findByRoomTypeIdOrderByDate(Long roomtypeid);
	
	DateWiseAvailableInventoryEntity findByRoomTypeIdAndDate(Long roomTypeId , LocalDate date);

}
