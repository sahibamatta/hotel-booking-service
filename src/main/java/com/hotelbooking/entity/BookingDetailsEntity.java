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
@Table(name = "booking_details")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class BookingDetailsEntity implements Serializable{

	public BookingDetailsEntity(String bookingId, String roomType, LocalDate bookingDate, LocalDate checkin, LocalDate checkout, Double totalPrice) {
		super();
		this.bookingId = bookingId;
		this.roomType = roomType;
		this.bookingDate = bookingDate;
		this.checkin = checkin;
		this.checkout = checkout;
		this.totalPrice = totalPrice;
	}
	private static final long serialVersionUID = 6524737679796339982L;

	@Id
	@GeneratedValue
	private Long id;
	@Column(name ="booking_id")
	private String bookingId;
	@Column(name ="room_type")
	private String roomType;
	@Column(name ="booking_date")
	private LocalDate bookingDate;
	@Column(name ="checkin")
	private LocalDate checkin;
	@Column(name ="checkout")
	private LocalDate checkout;
	@Column(name ="total_price")
	private Double totalPrice;
}
