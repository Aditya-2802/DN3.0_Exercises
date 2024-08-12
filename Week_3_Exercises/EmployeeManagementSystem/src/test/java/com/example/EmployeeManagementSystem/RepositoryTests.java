package com.example.EmployeeManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.EmployeeManagementSystem.model.Department;
import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.repository.DepartmentRepository;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;

@SpringBootTest
public class RepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testCreateAndFindEmployee() {
        Department department = new Department();
        department.setName("HR");
        department = departmentRepository.save(department);

        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setDepartment(department);
        employee = employeeRepository.save(employee);

        Employee foundEmployee = employeeRepository.findByEmail("john.doe@example.com");
        assertNotNull(foundEmployee);
        assertEquals("John Doe", foundEmployee.getName());
    }

    @Test
    public void testCreateAndFindDepartment() {
        Department department = new Department();
        department.setName("Finance");
        department = departmentRepository.save(department);

        Department foundDepartment = departmentRepository.findByName("Finance");
        assertNotNull(foundDepartment);
        assertEquals("Finance", foundDepartment.getName());
    }
}
