package com.jpmc.theater.service;

import static com.jpmc.theater.util.TheaterConstants.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Theater;
import com.jpmc.theater.util.LocalDateProvider;

@Component
public class TheaterServiceImpl  implements TheaterService {
	private Logger logger = LoggerFactory.getLogger(TheaterServiceImpl.class);
	private LocalDateProvider localDateProvider = LocalDateProvider.INSTANCE;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 * Print schedule in Simple Text Format.
	 * Currently it is not exposed to a client
	 * @param Theater
	 * @return 
	 */
	@Override
	public void printScheduleInTextFormat(Theater theater) {
		logger.info(localDateProvider.currentDate().toString());
		logger.info("Movie Title:  Seqence:  Show Start Date:  Show Start Time:  Movie Duration: Movie Price:  ");
		theater.getShowings().forEach(this::printScheduleInTextFormat);				
	}
	
	/**
	 * Print schedule in JSON Format. Using Jackson API to convert object to JSON. 
	 * Using writerWithDefaultPrettyPrinter method to print the JSON in human readable format 
	 * Currently it is not exposed to a client
	 * @param Theater
	 * @return 
	 */
	
	@Override
	public void printScheduleInJsonFormat(Theater theater) {
		theater.getShowings().forEach(s -> {
			try {
				printScheduleInJsonFormat(s);
			} catch (JsonProcessingException e) {
				logger.error(e.getMessage(), e);
			}
		});				
	}
	
	
	public void printScheduleInTextFormat(Showing showing) {
		StringBuilder scheduleBuilder = new StringBuilder();		
		String displayString = scheduleBuilder.append(scheduleBuilder.append(showing.getMovie().getTitle()).append(SPACE)
				.append(showing.getSequenceOfTheDay()).append(SPACE)
				.append(getShowingDateInHumanReadableFormat(showing.getShowStartTime())).append(SPACE)	
				.append(getShowingTimeInHumanReadableFormat(showing.getShowStartTime())).append(SPACE)	
				.append(humanReadableFormat(showing.getMovie().getRunningTime())).append(SPACE)
				.append(DOLLAR).append(showing.getMovie().getTicketPrice())).toString();
		logger.info(displayString);
		logger.info(DISPLAY_TEXT);		
	}
	
	private void printScheduleInJsonFormat(Showing showing) throws JsonProcessingException {	
		logger.info(localDateProvider.currentDate().toString());
		String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(showing);
		logger.info(jsonString);
		logger.info(DISPLAY_TEXT);		
	}
	
	private String getShowingDateInHumanReadableFormat(LocalDateTime showingDateTime) {      
        return showingDateTime.format(localDateProvider.getDateFormatter());
	}
	
	private String getShowingTimeInHumanReadableFormat(LocalDateTime showingDateTime) {      
        return showingDateTime.getHour() +  ":" + showingDateTime.getMinute();
	}
	
		
	private String humanReadableFormat(Duration duration) {
	        long hour = duration.toHours();
	        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());
	        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
	}
	 
	 // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
	    if (value == 1) {
	        return POSTFIX_ONE;
	    }
	    else {
	        return POSTFIX__MANY;
	    }
    } 
}
