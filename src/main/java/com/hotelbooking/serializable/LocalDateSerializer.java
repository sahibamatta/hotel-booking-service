package com.hotelbooking.serializable;

import java.lang.reflect.Type;

import org.joda.time.LocalDate;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


public class LocalDateSerializer implements JsonSerializer<LocalDate> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object, java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
		if (date != null) {
			return new JsonPrimitive(date.toString()); // "yyyy-mm-dd"
		}
		return null;
	}

}