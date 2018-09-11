package com.hotelbooking.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "roomtype_details")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class RoomtypeDetailsEntity implements Serializable{

	public RoomtypeDetailsEntity(String name, Integer rating, Double price) {
		super();
		this.name = name;
		this.rating = rating;
		this.price = price;
	}
	private static final long serialVersionUID = 6524737679796339982L;

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Integer rating;
	private Double price;
}
