package com.jpmc.theater.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.service.ReservationService;

/*
 * TheatherController is responsible for processing REST POST request to create Reservation
 */
@RestController
public class TheatherController {
	private Logger logger = LoggerFactory.getLogger(TheatherController.class);
	
	@Autowired
	private ReservationService reservationService;
	
	
	/**
	 * POST request to reserve Tickets/create new reservation. Spring will map the request parameter to Reservation object
	 * @param Reservation
	 * @return Reservation
	 */
	@PostMapping("/reserveTickets")
	public Reservation newReservation(@RequestBody Reservation reservation) {
		logger.info("New reservation request received");
	    return reservationService.reserveTickets(reservation);
	}
	

}
