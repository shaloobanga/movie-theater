package com.jpmc.theater.service;


import org.springframework.stereotype.Component;

import com.jpmc.theater.model.Reservation;
@Component
public interface ReservationService {
	Reservation reserveTickets(Reservation reservation) ;
}
