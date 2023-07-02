package com.jpmc.theater.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="showing")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Showing {	
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	private Movie movie;
		
	private int sequenceOfTheDay;
		
	private LocalDateTime showStartTime;

	@Override
	public int hashCode() {
		return Objects.hash(id, movie, sequenceOfTheDay, showStartTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Showing other = (Showing) obj;
		return Objects.equals(id, other.id) && Objects.equals(movie, other.movie)
				&& sequenceOfTheDay == other.sequenceOfTheDay && Objects.equals(showStartTime, other.showStartTime);
	}

	@Override
	public String toString() {
		return "Showing [id=" + id + ", movie=" + movie + ", sequenceOfTheDay=" + sequenceOfTheDay + ", showStartTime="
				+ showStartTime + "]";
	}
  		
	
}
