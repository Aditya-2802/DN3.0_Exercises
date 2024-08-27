package com.code.api.controllers;

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
import com.code.api.entity.Customer;
import com.code.api.services.CustomerService;
import com.code.api.services.BookService;

@Controller
@RequestMapping("/api/customers")
public class CustomerController {
	private CustomerService customerService;
	public CustomerController(CustomerService customerService) {
	    this.customerService = customerService;
	}
	
	@RequestMapping ("/test")
public String test()
{
		return "welcome";
}
	
@RequestMapping(value="register")
public String getRegister(Model model)
{
	model.addAttribute("customer", new Customer());
	return "registration";
}
//@PostMapping(value="/register")
//public Customer registerCustomer(
//        @RequestParam String name,
//        @RequestParam String email,
//        @RequestParam String password) {
//
//    Customer customer = new Customer(0,name, email, password);
//    Customer savedCustomer = customerService.addCustomer(customer);
//    return savedCustomer;
//}
//
@RequestMapping(value="save",method = RequestMethod.POST)
public String registerCustomer1(@ModelAttribute("customer") Customer customer) {

    //Customer customer = new Customer(0,name, email, password);
    Customer savedCustomer = customerService.addCustomer(customer);
    return "registration";
}

}
