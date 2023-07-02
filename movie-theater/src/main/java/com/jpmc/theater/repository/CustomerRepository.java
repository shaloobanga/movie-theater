package com.jpmc.theater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpmc.theater.model.Customer;

/*
 * Repository class to perform DB operations on Customer entity
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
}
