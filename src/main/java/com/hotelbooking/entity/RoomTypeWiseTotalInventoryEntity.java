package com.hotelbooking.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "roomtype_wise_inventory")
@Setter
@Getter
@ToString
public class RoomTypeWiseTotalInventoryEntity implements Serializable{

	private static final long serialVersionUID = 6524737679796339982L;

	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "room_type_id")
	private Long roomTypeId;
	@Column(name = "total_inventory")
	private Integer totalInventory;
}
