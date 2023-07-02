package com.jpmc.theater.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpmc.theater.model.Payment;

/*
 * Repository class to perform DB operations on Payment entity
 */

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
