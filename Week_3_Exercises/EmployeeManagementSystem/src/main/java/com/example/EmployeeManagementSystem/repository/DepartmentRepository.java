package com.example.EmployeeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.EmployeeManagementSystem.model.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Derived query methods
    Department findByName(String name);
}
