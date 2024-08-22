package com.code.employee.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.code.employee.entity.Employee;
import com.code.employee.projection.EmployeeProjection;
import com.code.employee.projection.EmployeeValueProjection;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	//Using the named query defined in Employee entity
	@Query(name="Employee.findByEmailNamed")
	Employee findByEmailNamed(@Param("email") String email);
	
	//Using the named query defined in Employee entity
	@Query(name = "Employee.findByDepartmentIdNamed")
	List<Employee>findByDepartmentIdNamed(@Param("departmentId") int departmentId);
	
	//Find all employees with pagination and sorting
	Page<Employee> findAll(Pageable pageable);
	
	//Find all employees with pagination and sorting
	Page<Employee> findByDepartmentId(int departmentId,Pageable pageable);
	
	//adding the query for employee projection
	
	@Query("SELECT e.name as name,e.email as email FROM Employee e")
	List<EmployeeProjection> findByEmployee();
	
	//adding the query for employee projection by value
	
	
	@Query("SELECT e.name as name,e.email as email FROM Employee e WHERE e.department.name =:departmentName")
	List<EmployeeValueProjection>findByDepartmentName1(@Param("departmentName") String departmentName);
	
	
}
