package com.jpmc.theater.config;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/*
 * TheaterConfig is a configuration class, properties for which will be injected by Spring from the properties file(in this case application.properties)
   These properties can be change without rebuilding or re-deploying the application
*/

@Data
@Component
@ConfigurationProperties
public class TheaterConfig {
	
	@Value("${movie.discount.amount.special}")
	private BigDecimal specialMovieDiscount;
	@Value("${movie.discount.amount.first.sequence}")	
	private BigDecimal firstSequenceDiscount;	
	@Value("${movie.discount.amount.second.sequence}")	
	private BigDecimal secondSequenceDiscount;
	@Value("${movie.discount.amount.day.month}")	
	private BigDecimal dayInMonthDiscount;	
	@Value("${movie.discount.amount.movie.run.time}")
	private BigDecimal movieTimeDiscount;
	@Value("${movie.discount.applicable.day.of.month}")
	private int dayInMonthDiscountIsApplicable;
	@Value("${movie.discount.applicable.run.start.time}")
	private int startTimeDiscountIsApplicable;
	@Value("${movie.discount.applicable.run.end.time}")
	private int endTimeDiscountIsApplicable;
	
	
	
	public int getDayInMonthDiscountIsApplicable() {
		return dayInMonthDiscountIsApplicable;
	}
	public void setDayInMonthDiscountIsApplicable(int dayInMonthDiscountIsApplicable) {
		this.dayInMonthDiscountIsApplicable = dayInMonthDiscountIsApplicable;
	}
	public int getStartTimeDiscountIsApplicable() {
		return startTimeDiscountIsApplicable;
	}
	public void setStartTimeDiscountIsApplicable(int startTimeDiscountIsApplicable) {
		this.startTimeDiscountIsApplicable = startTimeDiscountIsApplicable;
	}
	public int getEndTimeDiscountIsApplicable() {
		return endTimeDiscountIsApplicable;
	}
	public void setEndTimeDiscountIsApplicable(int endTimeDiscountIsApplicable) {
		this.endTimeDiscountIsApplicable = endTimeDiscountIsApplicable;
	}
	public BigDecimal getSpecialMovieDiscount() {
		return specialMovieDiscount;
	}
	public void setSpecialMovieDiscount(BigDecimal specialMovieDiscount) {
		this.specialMovieDiscount = specialMovieDiscount;
	}
	public BigDecimal getFirstSequenceDiscount() {
		return firstSequenceDiscount;
	}
	public void setFirstSequenceDiscount(BigDecimal firstSequenceDiscount) {
		this.firstSequenceDiscount = firstSequenceDiscount;
	}
	public BigDecimal getSecondSequenceDiscount() {
		return secondSequenceDiscount;
	}
	public void setSecondSequenceDiscount(BigDecimal secondSequenceDiscount) {
		this.secondSequenceDiscount = secondSequenceDiscount;
	}
	public BigDecimal getDayInMonthDiscount() {
		return dayInMonthDiscount;
	}
	public void setDayInMonthDiscount(BigDecimal dayInMonthDiscount) {
		this.dayInMonthDiscount = dayInMonthDiscount;
	}
	public BigDecimal getMovieTimeDiscount() {
		return movieTimeDiscount;
	}
	public void setMovieTimeDiscount(BigDecimal movieTimeDiscount) {
		this.movieTimeDiscount = movieTimeDiscount;
	}
	
}
