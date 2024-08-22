package com.code.department.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.code.department.entity.Department;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	//Derived query methods
	Department findByName(String name);
	//Custom JPQL query to find a department by name
	@Query("SELECT d FROM Department d WHERE d.name=:name")
	Department findByNameUsingJPQL(@Param("name")String name);
	
	
	//Custom native SQL query to find all departments with a specific name pattern
	//By  default nativeQuery= false so when we want to use sql query native query set to true
	
	@Query(value="SELECT * FROM departments d WHERE d.name LIKE % :pattern%",nativeQuery=true)
	List<Department>findByNamePattern(@Param("pattern")String pattern);

}
