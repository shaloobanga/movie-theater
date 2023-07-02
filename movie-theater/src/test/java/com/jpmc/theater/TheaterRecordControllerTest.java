package com.jpmc.theater;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.hamcrest.Matchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.theater.controller.TheaterController;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Payment;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.repository.PaymentRepository;
import com.jpmc.theater.repository.ReservationRepository;

@WebMvcTest(TheaterController.class)
public class TheaterRecordControllerTest {
	@Autowired
	MockMvc mockMvc;
	 
    @Autowired
    ObjectMapper mapper;
	    
	@MockBean
	ReservationRepository reservationRecordRepository;
	
	@MockBean
	PaymentRepository paymentRepository;
	
	private Customer customer;
    private Movie movie;
	private Showing showing;
	private Reservation  reservation;
	private Payment payment;
	
	@BeforeEach
	public void setup() {	
		customer = new Customer(1l, "Rayven", "Cebu Philippines");
		movie = new Movie(2l,"Turning Red", Duration.ofMinutes(85), new BigDecimal(11), true);
		showing  = new Showing(1l,movie, 5, LocalDateTime.of(LocalDate.of(2022,1,1), LocalTime.of(9, 0)));
		payment = new Payment(1l,BigDecimal.valueOf(8.8));
		reservation = new Reservation(1L,customer, showing, 1, payment);
	}	
	
	 @Test
	 public void createRecordTest() throws Exception {
		 when(reservationRecordRepository.save(reservation)).thenReturn(reservation);
		 when(paymentRepository.save(any())).thenReturn(payment);
		 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/reserveTickets")
		            .contentType(MediaType.APPLICATION_JSON)
		            .accept(MediaType.APPLICATION_JSON)
		            .content(this.mapper.writeValueAsString(reservation));
		 
		 //Total Price is 8.8 as special discount is applied
		 mockMvc.perform(mockRequest)
		            .andExpect(status().isOk())
		            .andExpect(jsonPath("$", notNullValue()))
		            .andExpect(jsonPath("$.payment.totalPrice", is(8.8)));
	 }
}
