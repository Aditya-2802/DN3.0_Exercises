package com.code.api.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.code.api.dto.CustomerDTO;
import com.code.api.dto.LoginDTO;
import com.code.api.dto.RegisterDTO;
import com.code.api.entity.Customer;
import com.code.api.repository.CustomerRepository;

@Service
public class AuthenticationService {
    private final CustomerRepository customerRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        CustomerRepository customerRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer signup(RegisterDTO input) {
    	
        Customer customer = new Customer();
        		
        		//customer.setEmailid(null);
                customer.setName(input.getName());
                customer.setEmailid(input.getEmail());
                customer.setPassword(passwordEncoder.encode(input.getPassword()));

        return customerRepository.save(customer);
    }

    public Customer authenticate(LoginDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return customerRepository.findByEmailid(input.getEmail())
                .orElseThrow();
    }
}