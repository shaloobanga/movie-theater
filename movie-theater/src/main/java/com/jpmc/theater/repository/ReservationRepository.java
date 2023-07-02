package com.jpmc.theater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpmc.theater.model.Reservation;

/*
 * Repository class to perform DB operations on Reservation entity
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
