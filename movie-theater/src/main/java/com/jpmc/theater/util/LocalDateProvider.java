package com.jpmc.theater.util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/*
 * Java Singleton to provide date/time related methods
 * It is created as Enum to no need to worry about creating new instance during serialization/deserialization or using reflection 
 * As this application is using Spring, and spring beans are singleton in one application context, we could create it as Spring bean and let Spring manage the life cycle. 
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
