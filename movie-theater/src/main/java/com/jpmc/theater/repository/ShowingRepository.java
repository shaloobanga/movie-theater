package com.jpmc.theater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpmc.theater.model.Showing;

/*
 * Repository class to perform DB operations on Showing entity
 */

@Repository
public interface ShowingRepository extends JpaRepository<Showing, Long> {

}
