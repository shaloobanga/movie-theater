package com.jpmc.theater.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="reservation")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Reservation {    
	@Id @GeneratedValue
	private Long id;
	
	@OneToOne
	private Customer customer;
		
	@OneToOne
    private Showing showing;
		
	private int audienceCount;
	@OneToOne
	private Payment payment;
		
	
}