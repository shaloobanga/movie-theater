package com.jpmc.theater.model;

import java.math.BigDecimal;
import java.time.Duration;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name="movie")
public class Movie {	
	@Id @GeneratedValue
	private Long id;	

	private String title;
	       
    private Duration runningTime;
    
    private BigDecimal ticketPrice;
    
    private boolean isSpecial;

	
}