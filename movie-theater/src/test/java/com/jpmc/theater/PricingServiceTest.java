package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.PricingService;

@SpringBootTest
public class PricingServiceTest {	
	@Autowired
	private PricingService pricingService;
	private Movie movie;
	private Showing showing;
	
	@BeforeEach
	public void setup() {	
		movie = new Movie(2l,"Turning Red", Duration.ofMinutes(85), new BigDecimal(11), false);
		showing  = new Showing(1l,movie, 5, LocalDateTime.of(LocalDate.of(2022,1,1), LocalTime.of(9, 0)));
	}	
	
	//Test to verify no discount is applied when it is not applicable
	@Test
	public void calculateFinalTicketPrice_NoDiscountApplicable_NoDiscountApplied() throws Exception {    	
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 1).getTotalPrice();
    	assertTrue(movie.getTicketPrice().compareTo(calculatedTicketPrice) == 0);
    }
	//Test to verify max discount is applied when all the discounts are applicable
	@Test
	public void calculateFinalTicketPrice_AllDiscountApplicable_MaxFirstSeqDiscountApplied() throws Exception {    	
		showing.setSequenceOfTheDay(1);		
		movie.setSpecial(true);
		showing.setShowStartTime(LocalDateTime.of(LocalDate.of(2022, 1, 7), LocalTime.of(13, 0)));
		BigDecimal actualTicketAmount = BigDecimal.valueOf(16);
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }
	//Test to verify for special movie discount	
	@Test
	public void calculateFinalTicketPrice_SpecialDiscountApplicable_DiscountApplied() throws Exception {    	
		movie.setSpecial(true);
		BigDecimal actualTicketAmount = BigDecimal.valueOf(17.6);
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }
	
	//Test to verify for First in sequence movie discount
	@Test
	public void calculateFinalTicketPrice_FirstInSeqDiscountApplicable_DiscountApplied() throws Exception {    	
		showing.setSequenceOfTheDay(1);		
		BigDecimal actualTicketAmount = BigDecimal.valueOf(16);
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }
	//Test to verify for Second in sequence movie discount
	@Test
	public void calculateFinalTicketPrice_SecondInSeqDiscountApplicable_DiscountApplied() throws Exception {    	
		showing.setSequenceOfTheDay(2);		
		BigDecimal actualTicketAmount = BigDecimal.valueOf(18);
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }
	
	//Test to verify for Month of the day movie discount
	@Test
	public void calculateFinalTicketPrice_MonthOfTheDayDiscountApplicable_DiscountApplied() throws Exception {    	
		showing.setShowStartTime(LocalDateTime.of(LocalDate.of(2022, 1, 7), LocalTime.of(9, 0)));
		BigDecimal actualTicketAmount = BigDecimal.valueOf(20);
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }
	//Test to verify for movie start time discount 
	@Test
	public void calculateFinalTicketPrice_MovieStartTimeBetween11And4DiscountApplicable_DiscountApplied() throws Exception {    	
		showing.setShowStartTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 0)));
		BigDecimal actualTicketAmount = BigDecimal.valueOf(16.5);
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }	
	
	//Test to verify for movie start time discount at 11 (edge case)
	@Test
	public void calculateFinalTicketPrice_MovieStartTimeAt11DiscountApplicable_DiscountApplied() throws Exception {    	
		showing.setShowStartTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
		BigDecimal actualTicketAmount = BigDecimal.valueOf(16.5);
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }
	
	//Test to verify for movie start time discount at 4PM (edge case)
	@Test
	public void calculateFinalTicketPrice_MovieStartTimeAt4DiscountApplicable_DiscountApplied() throws Exception {    	
		showing.setShowStartTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)));
		BigDecimal actualTicketAmount = BigDecimal.valueOf(16.5);
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }
	
	//Test to verify max discount is applied when movie is Special and first in sequence	
	@Test
	public void calculateFinalTicketPrice_FirstInSeqAndSpecialDiscountApplicable_MaxFirstInSeqDiscountApplied() throws Exception {    	
		showing.setSequenceOfTheDay(1);		
		movie.setSpecial(true);;
		BigDecimal actualTicketAmount = BigDecimal.valueOf(16);
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }
	
	//Test to verify max discount is applied when movie is special and second in sequence	
	@Test
	public void calculateFinalTicketPrice_SecondInSeqAndSpecialDiscountApplicable_SpecialDiscountApplied() throws Exception {    	
		showing.setSequenceOfTheDay(2);		
		movie.setSpecial(true);;
		BigDecimal actualTicketAmount = BigDecimal.valueOf(17.6);
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }
	//Test to verify max discount is applied when showing on 7th and and movie start time is between 11am-4pm
	@Test
	public void calculateFinalTicketPrice_MovieStartTimeAndMonthOfTheDayDiscountApplicable_MaxMovieStartTimeDiscountApplied() throws Exception {	
		showing.setShowStartTime(LocalDateTime.of(LocalDate.of(2022, 1, 7), LocalTime.of(13, 0)));
		BigDecimal actualTicketAmount = BigDecimal.valueOf(16.5);		
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }	
	
	//Test to verify max discount is applied when movie is first in sequence, special and showing on 7th	
	@Test
	public void calculateFinalTicketPrice_FirstInSeqAndSpecialAndMonthOfTheDayDiscountApplicable_MaxFirstInSeqDiscountApplied() throws Exception { 
		showing.setSequenceOfTheDay(1);		
		movie.setSpecial(true);
		showing.setShowStartTime(LocalDateTime.of(LocalDate.of(2022, 1, 7), LocalTime.of(9, 0)));
		BigDecimal actualTicketAmount = BigDecimal.valueOf(16);		
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }
	
	//Test to verify max discount is applied when movie is second in sequence, special and showing on 7th	
	@Test
	public void calculateFinalTicketPrice_SecondInSeqAndSpecialAndMonthOfTheDayDiscountApplicable_MaxSpecialInSeqDiscountApplied() throws Exception { 
		showing.setSequenceOfTheDay(2);		
		movie.setSpecial(true);
		showing.setShowStartTime(LocalDateTime.of(LocalDate.of(2022, 1, 7), LocalTime.of(9, 0)));
		BigDecimal actualTicketAmount = BigDecimal.valueOf(17.6);		
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }
	
	//Test to verify max discount is applied when movie is first in sequence, special and movie start time is between 11am-4pm
	@Test
	public void calculateFinalTicketPrice_FirstInSeqAndSpecialAndMovieStartTimeDiscountApplicable_MaxFirstInSeqDiscountApplied() throws Exception { 
		showing.setSequenceOfTheDay(1);		
		movie.setSpecial(true);
		showing.setShowStartTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 0)));
		BigDecimal actualTicketAmount = BigDecimal.valueOf(16);
		showing.setShowStartTime(LocalDateTime.of(LocalDate.of(2022, 1, 7), LocalTime.of(9, 0)));
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }
	//Test to verify max discount is applied when movie is second in sequence ,special and movie start time is between 11am-4pm
	@Test
	public void calculateFinalTicketPrice_SecondInSeqAndSpecialAndMovieStartTimeDiscountApplicable_MaxMovieStartTimeDiscountApplied() throws Exception { 
		showing.setSequenceOfTheDay(2);		
		movie.setSpecial(true);
		showing.setShowStartTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 0)));
		BigDecimal actualTicketAmount = BigDecimal.valueOf(16.5);		
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }
	
	//Test to verify max discount is applied when movie is special, showing time is on 7th, and movie start time is between 11am-4pm	
	@Test
	public void calculateFinalTicketPrice_SpecialAndMovieStartTimeAndMonthOfTheDayDiscountApplicable_MaxFirstInSeqDiscountApplied() throws Exception {	
		movie.setSpecial(true);
		showing.setShowStartTime(LocalDateTime.of(LocalDate.of(2022, 1, 7), LocalTime.of(13, 0)));
		BigDecimal actualTicketAmount = BigDecimal.valueOf(16.5);		
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }	
	
		
	//Test to verify max discount is applied when movie is first in sequence, showing time is on 7th, and movie start time is between 11am-4pm	
	@Test
	public void calculateFinalTicketPrice_FirstInSeqAndMovieStartTimeAndMonthOfTheDayDiscountApplicable_MaxFirstInSeqDiscountApplied() throws Exception {	
		showing.setSequenceOfTheDay(1);		
		showing.setShowStartTime(LocalDateTime.of(LocalDate.of(2022, 1, 7), LocalTime.of(13, 0)));
		BigDecimal actualTicketAmount = BigDecimal.valueOf(16);		
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }	
	//Test to verify max discount is applied when movie is second  in sequence, showing time is on 7th, and movie start time is between 11am-4pm	
	@Test
	public void calculateFinalTicketPrice_SecondInSeqAndMovieStartTimeAndMonthOfTheDayDiscountApplicable_MaxStartTimeInSeqDiscountApplied() throws Exception {	
		showing.setSequenceOfTheDay(2);		
		showing.setShowStartTime(LocalDateTime.of(LocalDate.of(2022, 1, 7), LocalTime.of(13, 0)));
		BigDecimal actualTicketAmount = BigDecimal.valueOf(16.5);		
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }	
	
	//Test to verify max discount is applied when movie is special, showing time is on 7th, and movie start time is between 11am-4pm	
	@Test
	public void calculateFinalTicketPrice_SpecialMovieAndMovieStartTimeAndMonthOfTheDayDiscountApplicable_MaxMovieStartTimeDiscountApplied() throws Exception {	
		movie.setSpecial(true);
		showing.setShowStartTime(LocalDateTime.of(LocalDate.of(2022, 1, 7), LocalTime.of(13, 0)));
		BigDecimal actualTicketAmount = BigDecimal.valueOf(16.5);		
		BigDecimal calculatedTicketPrice = pricingService.calculateFinalTicketPrice(showing, 2).getTotalPrice();
		;
    	assertTrue(actualTicketAmount.compareTo(calculatedTicketPrice) == 0);
    }	
		

	
	
	
}
