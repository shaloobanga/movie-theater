package com.jpmc.theater.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpmc.theater.model.Payment;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.repository.ReservationRepository;

@Component
public class ReservationServiceImpl implements ReservationService {
	private Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	private PricingService pricingService;
	
	@Autowired
	public ReservationServiceImpl(PricingService pricingService) {
		this.pricingService = pricingService;
	}
	/*
	 * Create a new Reservation, calls PricingService to calculate the final discount
	 * @param reservation 
	 * @return new reservation object with final ticket price
	 */
	@Override
	public Reservation reserveTickets(Reservation reservation) {	
		logger.info("Started reserving tickets");
		Payment payment = pricingService.calculateFinalTicketPrice(reservation.getShowing(), reservation.getAudienceCount());		
		reservation.setPayment(payment);		
		reservationRepository.save(reservation);	
		logger.info("Successfully created reservation with Id: {} and ticket price {}, ", reservation.getId(), payment.getTotalPrice());
		return reservation;
	}
	
}
