package com.code.api.services;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.code.api.entity.Customer;
import com.code.api.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {

		@Autowired
		CustomerRepository customerRepository;
		 
	public Customer addCustomer(Customer customer)
	{
		return customerRepository.save(customer);
	}

public List<Customer> getAllCustomer()
{
	return customerRepository.findAll();
}
}
