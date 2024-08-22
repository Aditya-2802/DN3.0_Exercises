package com.code.employee.entity;


	import lombok.AllArgsConstructor;
	import lombok.Getter;

	@Getter
	@AllArgsConstructor
	public class EmployeeDTO {

	    private String name;
	    private String email;
	    private double salary;
	    private int deptid;
	}


