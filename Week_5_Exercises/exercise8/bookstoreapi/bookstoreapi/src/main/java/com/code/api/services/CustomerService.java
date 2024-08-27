package com.code.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.api.dto.CustomerDTO;
import com.code.api.entity.Customer;
import com.code.api.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {

		@Autowired
		CustomerRepository customerRepository;
		 @Autowired
		    private ModelMapper modelMapper;
	public Customer addCustomer(Customer customer)
	{
		return customerRepository.save(customer);
	}
private	<S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}
public List<CustomerDTO> getCustomer()
{
	List<Customer> customers=customerRepository.findAll();
	List<CustomerDTO> customerDTOs=mapList(customers,CustomerDTO.class);
	return customerDTOs;
}
public List<Customer> getAllCustomer()
{
	List<Customer> customers=customerRepository.findAll();
	
	return customers;
}
//public Customer getCustomerById(int id)
//{
//	return customerRepository.findById(id).get();
//}
public CustomerDTO getCustomerById(int id)
{
	Customer customer= customerRepository.findById(id).get();
	CustomerDTO customerDTO=this.modelMapper.map(customer, CustomerDTO.class);
	return customerDTO;
}

public void deletById(int id)
{
	 customerRepository.deleteById(id);
}
}
