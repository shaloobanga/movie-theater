package com.jpmc.theater;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Theater;
import com.jpmc.theater.service.TheaterService;

@SpringBootTest
public class TheaterServiceTest {
	@Autowired
	TheaterService theaterService;
	 
	private static Movie movie;
	private static Showing showing1;
	private static Showing showing2;
	private static Theater theater;
	
	@BeforeAll
	public static void setup() {	
		movie = new Movie(2l,"Turning Red", Duration.ofMinutes(85), new BigDecimal(11), false);
		showing1  = new Showing(1l,movie, 5, LocalDateTime.of(LocalDate.of(2022,1,1), LocalTime.of(9, 0)));
		showing2  = new Showing(2l,movie, 2, LocalDateTime.of(LocalDate.of(2023,2,2), LocalTime.of(11, 0)));
		theater = new Theater(1L, List.of(showing1,showing2));
	}	
	
	@Test
	public void testPrintScheduleInTextFormat() {
		theaterService.printScheduleInTextFormat(theater);
	}
	

	@Test
	public void testPrintScheduleInJsonFormat() {
		theaterService.printScheduleInJsonFormat(theater);
	}
		
	    
}
