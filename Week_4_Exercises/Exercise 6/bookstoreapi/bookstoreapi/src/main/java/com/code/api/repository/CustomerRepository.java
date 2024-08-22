package com.code.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.api.entity.Customer;
@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Integer>{
	 //Optional<Customer> findByUsername(String username);
	 
	 Optional<Customer> findByEmailid(String email);
	  //Boolean existsByEmail(String email);

}
