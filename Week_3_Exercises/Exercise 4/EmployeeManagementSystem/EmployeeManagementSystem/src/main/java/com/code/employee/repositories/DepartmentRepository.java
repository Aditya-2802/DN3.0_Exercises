package com.code.employee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.employee.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	//Derived query methods
	Department findByName(String name);

}
