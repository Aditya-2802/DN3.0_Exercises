package com.code.employee.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.code.employee.entity.Employee;
import com.code.employee.projection.EmployeeProjection;
import com.code.employee.projection.EmployeeValueProjection;


//@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	// Using the named query defined in Employee entity
   // @Query(name = "Employee.findByEmailNamed")
   // Employee findByEmailNamed(@Param("email") String email);

    
    
 // Find all employees with pagination and sorting
    Page<Employee> findAll(Pageable pageable);
    
 
}
