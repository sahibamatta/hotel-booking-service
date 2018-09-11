package com.hotelbooking.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "datewise_available_inventory")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class DateWiseAvailableInventoryEntity implements Serializable{

	public DateWiseAvailableInventoryEntity(Long roomTypeId, LocalDate date, Integer availableInventory, Integer bookingProgressCount) {
		super();
		this.roomTypeId = roomTypeId;
		this.date = date;
		this.availableInventory = availableInventory;
		this.bookingProgressCount = bookingProgressCount;
	}
	private static final long serialVersionUID = 6524737679796339982L;

	@Id
	@GeneratedValue
	private Long id;
	@Column(name ="room_type_id")
	private Long roomTypeId;
	private LocalDate date;
	@Column(name ="available_inventory")
	private Integer availableInventory;
	@Column(name = "booking_progress_count")
	private Integer bookingProgressCount;
}
