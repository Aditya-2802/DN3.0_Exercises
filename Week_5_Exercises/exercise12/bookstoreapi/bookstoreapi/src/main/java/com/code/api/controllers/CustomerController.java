package com.code.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.code.api.entity.Customer;
import com.code.api.services.CustomerService;

import jakarta.validation.Valid;

import com.code.api.services.BookService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.api.dto.CustomerDTO;
import com.code.api.entity.Customer;
import com.code.api.services.CustomerService;
import com.code.api.services.BookService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	private CustomerService customerService;
	public CustomerController(CustomerService customerService) {
	    this.customerService = customerService;
	}
	
	@GetMapping ("/test")
public String test()
{
		return "welcome";
}
	

@PostMapping("/register")
public ResponseEntity<Customer> registerCustomer(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam String password) {

    Customer customer = new Customer(0,name, email, password);
    Customer savedCustomer = customerService.addCustomer(customer);
    return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
}
@GetMapping("/")
public ResponseEntity<List<CustomerDTO>> getAllCustomer(){
      
  return new ResponseEntity<>(customerService.getCustomer(),HttpStatus.OK);
   
}
@GetMapping("/{id}")
public ResponseEntity<CustomerDTO> getCustomerById(@Valid @PathVariable int id){
      
  return new ResponseEntity<>(customerService.getCustomerById(id),HttpStatus.OK);
   
}
@PutMapping("edit/{id}")
public ResponseEntity<CustomerDTO> updateCustomer(@Valid @PathVariable int id,@Valid @RequestBody Customer customer){
    
	  return new ResponseEntity<>(customerService.updateCustomer(id,customer),HttpStatus.OK);
	   
	}
@DeleteMapping("delete/{id}")
public ResponseEntity<HttpStatus> getDeleteById(@Valid @PathVariable int id){
{
	
	      
		  return customerService.deletById(id);
		   
		}
}
@GetMapping("/me")
public ResponseEntity<Customer> authenticatedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    Customer currentUser = (Customer) authentication.getPrincipal();

    return ResponseEntity.ok(currentUser);
}

}
