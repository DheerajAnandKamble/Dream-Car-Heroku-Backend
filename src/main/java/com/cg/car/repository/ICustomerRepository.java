package com.cg.car.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.car.entity.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

	// CUSTOM METHOD
	List<Customer> findByCity(String city);
	
	Customer findCustomerByEmail(String email);

}
