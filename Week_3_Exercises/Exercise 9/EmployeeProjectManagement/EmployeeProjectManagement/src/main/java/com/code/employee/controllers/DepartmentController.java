package com.code.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.department.entity.Department;
import com.code.department.repositories.DepartmentRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;



@RestController
@RequestMapping("api/department")
public class DepartmentController {
	
	@Autowired
    private DepartmentRepository departmentRepository;

   // @Transactional("secondaryTransactionManager")
	
	@GetMapping(value="test")
	public String test() {
		return "Welcome to Spring Boot API"; 
	}
	@PostMapping(value="add")
	public Department addDepartment(@RequestBody Department department)
	{
		return departmentRepository.save(department);
	}
//	
	@GetMapping(value="/name/{name}")
	public Department getAllDepartmentByName(@PathVariable String name)
	{
		return departmentRepository.findByNameUsingJPQL(name);
	}
	@GetMapping(value="/pattern/{pattern}")
	public List<Department> getAllDepartmentByPattern(@PathVariable String pattern)
	{
		return departmentRepository.findByNamePattern(pattern);
	}
}
