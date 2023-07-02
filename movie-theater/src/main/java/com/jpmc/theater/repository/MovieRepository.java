package com.jpmc.theater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpmc.theater.model.Movie;

/*
 * Repository class to perform DB operations on Movie entity
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

}
