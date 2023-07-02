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

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.repository.CustomerRepository;
import com.jpmc.theater.repository.MovieRepository;
import com.jpmc.theater.repository.ReservationRepository;
import com.jpmc.theater.repository.ShowingRepository;
import com.jpmc.theater.service.ReservationServiceImpl;

@SpringBootTest
public class ReservationServiceTest {
	 @Autowired    
	 private CustomerRepository customerRepository;
	 
	 @Autowired
	 private MovieRepository movieRepository;
	 
	 @Autowired
	 private ShowingRepository showingRepository;
	 
	 @Autowired
	 private ReservationRepository reservatiomRepository;
	 
	 @Autowired
	 ReservationServiceImpl reservationService;
	 		
	 private static Customer customer;
	 private static Movie movie;
	 private static Showing showing;
	 
	 @BeforeEach
	 public void setupData() {
		customer = getCustomer();
		movie = getMovie();
		showing = getShowing(movie);
	 }
	 
	 @Test
	 public void testReserveTickets_CreateNewReservationObject_ReturnsNewOject() {
    	BigDecimal actualTicketPrice = BigDecimal.valueOf(11);
    	Reservation reservation = new Reservation();
    	reservation.setCustomer(customer);
    	reservation.setShowing(showing);
    	reservation.setAudienceCount(1);
    	reservatiomRepository.save(reservation);
    	reservation = reservationService.reserveTickets(reservation);    	
    	assertTrue(actualTicketPrice.compareTo(reservation.getPayment().getTotalPrice()) == 0);
    	assertTrue(reservation.getId()!=null);
	 }	 
	 
	 @Test
	 public void testReserveTickets__CalculateFinalTicketPriceAfterDiscount_ReturnsFinalPrice() {
    	BigDecimal actualTicketPrice = BigDecimal.valueOf(11);
    	Reservation reservation = new Reservation();
    	reservation.setCustomer(customer);
    	reservation.setShowing(showing);
    	reservation.setAudienceCount(1);
    	reservatiomRepository.save(reservation);
    	reservation = reservationService.reserveTickets(reservation);    	
    	assertTrue(actualTicketPrice.compareTo(reservation.getPayment().getTotalPrice()) == 0);    
	 }	 
	    
	 private Customer getCustomer() {
    	Customer customer = new Customer();
    	customer.setFirstName("Joe");
    	customer.setFirstName("Meth");
    	customerRepository.save(customer);
    	return customer;
	 }
	    
    private Movie getMovie() {
    	Movie movie = new Movie();
    	movie.setSpecial(false);
    	movie.setRunningTime(Duration.ofMinutes(85));
    	movie.setTitle("Turning Red");
    	movie.setTicketPrice(BigDecimal.valueOf(11));
    	movieRepository.save(movie);
    	return movie;    	
    }
	    
    private Showing getShowing(Movie movie) {
    	Showing showing = new Showing();
    	showing.setMovie(movie);
    	showing.setSequenceOfTheDay(5);
    	showing.setShowStartTime(LocalDateTime.of(LocalDate.of(2022,1,1), LocalTime.of(9, 0)));
    	showingRepository.save(showing);
    	return showing;
    }
	   
}
