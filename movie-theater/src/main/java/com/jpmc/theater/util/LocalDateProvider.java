package com.jpmc.theater.util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/*
 * Singleton to provide date/time related methods
 * It is created as Enum to no need to worry about creating new instance during serialization/deserialization or using reflection 
 */
public enum LocalDateProvider {
	INSTANCE;

    public LocalDate currentDate() {
        return LocalDate.now();
    }
    public DateTimeFormatter getDateFormatter() {
    	return DateTimeFormatter.ISO_LOCAL_DATE;
    }
}
