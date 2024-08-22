package com.code.employee.controllers;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.code.department.entity.Department;
import com.code.employee.entity.Employee;
import com.code.employee.entity.EmployeeDTO;
import com.code.employee.projection.EmployeeProjection;
import com.code.employee.projection.EmployeeValueProjection;
//import com.code.employee.repositories.DepartmentRepository;
import com.code.employee.repositories.EmployeeRepository;

import jakarta.transaction.Transactional;


@RestController
@Transactional
@RequestMapping("api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository  employeeRepository;
   
    @GetMapping(value="test")
	public String test() {
		return "Welcome to Spring Boot API"; 
	}
    
    
    // Create a new Employee
    @PostMapping(value="/add")
    public Employee createEmployee(@RequestBody EmployeeDTO employeeDTO) {
    
    	System.out.println("name"+employeeDTO.getName());
    	System.out.println("name"+employeeDTO.getDeptid());
    	//Department department= departmentRepository.findById(employeeDTO.getDeptid()).get();
    	
    	Employee employee=new Employee();
    	//employee.setDepartment(department);
    	employee.setEmail(employeeDTO.getEmail());
    	employee.setName(employeeDTO.getName());
    	employee.setSalary(employeeDTO.getSalary());
        return employeeRepository.save(employee);
    }

    // Get all Employees
    @GetMapping("list")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
  
   
    // Get a single Employee by ID
    @GetMapping("{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        Employee employee = employeeRepository.findById(id).get();
        return employee;
    }
    
//    @GetMapping("/email/{emailid}")
//    public Employee getEmployeeByEmailId(@PathVariable String emailid) {
//        Employee employee = employeeRepository.findByEmailNamed(emailid);
//        return employee;
//    }
   
 // Get all Employees with pagination and sorting
    @GetMapping("page")
    public Page<Employee> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return employeeRepository.findAll(pageable);
    }

    // Get employees by department with pagination and sorting
   
    // Update an Employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employeeDet) {
    	
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(employeeDet.getName());
            employee.setEmail(employeeDet.getEmail());
            //employee.setDepartment(employeeDet.getDepartment());
            Employee updatedEmployee = employeeRepository.save(employee);
            
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
       
    }

    // Delete an Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeRepository.delete(optionalEmployee.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


