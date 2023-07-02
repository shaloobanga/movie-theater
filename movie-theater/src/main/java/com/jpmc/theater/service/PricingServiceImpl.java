package com.jpmc.theater.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpmc.theater.config.TheaterConfig;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Payment;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.repository.PaymentRepository;

/**
 * @author shaloo
 *
 */
@Component
public class PricingServiceImpl implements  PricingService {
	
	private Logger logger = LoggerFactory.getLogger(PricingServiceImpl.class);
	
	private TheaterConfig config;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	public PricingServiceImpl(TheaterConfig config) {
		this.config = config;
	}
	
	/**
	 * 	This method calculates the final ticket price after applying the biggest applicable discount
	 * @param showing
	 * @param audianceCount
	 * @return Payment object with final ticket price
	*/
	@Override
	public Payment calculateFinalTicketPrice(Showing showing, int audianceCount) {
		logger.info("Started caculating final tickert price after discount");
		Movie movie = showing.getMovie();
		BigDecimal ticketPriceAfterDiscount = movie.getTicketPrice().subtract(calculateDiscount(showing)) ;
		BigDecimal finalTicketPrice =  ticketPriceAfterDiscount.multiply(BigDecimal.valueOf(audianceCount));
		logger.info("Finished caculating final tickert price after discount "+ finalTicketPrice);
		return paymentRepository.save(new Payment(finalTicketPrice));
	
	}
	
	private BigDecimal calculateDiscount(Showing showing) {	
		BigDecimal specialMovieDicount = calcuateSpecialMovieDiscount(showing.getMovie());
		BigDecimal sequenceDicount = calculateSequencDiscount(showing);
		BigDecimal maxDiscount = specialMovieDicount.compareTo(sequenceDicount) == 1 ? specialMovieDicount : sequenceDicount;
		BigDecimal dayofMonthDiscount = calculateDayOfTheMonthDiscount(showing);
		maxDiscount = maxDiscount.compareTo(dayofMonthDiscount) == 1 ? maxDiscount : dayofMonthDiscount;
		BigDecimal movieStartingTimeDiscount = calculateMovieStartTimeDiscount(showing);
		maxDiscount = maxDiscount.compareTo(movieStartingTimeDiscount) == 1 ? maxDiscount : movieStartingTimeDiscount;
		return maxDiscount;
	}

	private BigDecimal calcuateSpecialMovieDiscount(Movie movie) {
		if(movie.isSpecial()) {
			logger.info("Special movie discount is applicable on movie Id {}, " , movie.getId());
			return movie.getTicketPrice().multiply(config.getSpecialMovieDiscount());
		}
		return BigDecimal.valueOf(0);
	}
	
	private BigDecimal calculateSequencDiscount(Showing showing) {
		if(showing.getSequenceOfTheDay()==1) {
			logger.info("First sequence discount is applicable on movie {}" , showing.getMovie().getId());
			return config.getFirstSequenceDiscount();
		}
		else if(showing.getSequenceOfTheDay()==2) {
			logger.info("Second sequence discount is applicable on movie {}" , showing.getMovie().getId());
			return config.getSecondSequenceDiscount();
		}
		return BigDecimal.valueOf(0);
	}
	
	private BigDecimal calculateDayOfTheMonthDiscount(Showing showing) {		
		if(showing.getShowStartTime().getDayOfMonth() == config.getDayInMonthDiscountIsApplicable()) {
			logger.info("Seventh Day of the month discount is applicable on showing {}" , showing.getId());
			return config.getDayInMonthDiscount();
		}
		return BigDecimal.valueOf(0);
	}
	
	private BigDecimal calculateMovieStartTimeDiscount(Showing showing) {
		int hour = showing.getShowStartTime().getHour();
		if(hour>=config.getStartTimeDiscountIsApplicable() && hour<=config.getEndTimeDiscountIsApplicable()) {
			logger.info("Showing starting time discount is applicable for showing {}" , showing.getId());
			return showing.getMovie().getTicketPrice().multiply(config.getMovieTimeDiscount());
		}
		return BigDecimal.valueOf(0);
	}
}
