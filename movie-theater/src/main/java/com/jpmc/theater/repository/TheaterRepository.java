package com.jpmc.theater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jpmc.theater.model.Theater;

/*
 * Repository class to perform DB operations on Theater entity
 */
@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

}
