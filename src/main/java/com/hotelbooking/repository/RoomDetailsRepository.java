package com.hotelbooking.repository;

import org.springframework.data.repository.CrudRepository;

import com.hotelbooking.entity.RoomtypeDetailsEntity;

public interface RoomDetailsRepository extends CrudRepository<RoomtypeDetailsEntity,Long>{
	
	RoomtypeDetailsEntity findByName(String name);

}