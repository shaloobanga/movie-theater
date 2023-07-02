package com.jpmc.theater.service;

import org.springframework.stereotype.Component;

import com.jpmc.theater.model.Payment;
import com.jpmc.theater.model.Showing;

@Component
public interface PricingService {
	Payment calculateFinalTicketPrice(Showing showing, int audianceCount);
}
