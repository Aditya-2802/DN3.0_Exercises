package com.code.employee.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name="departments")
@Getter
@Setter
public class Department {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//adding constraints unique=true not allow duplicate departmentname
	@Column(nullable=false,unique=true)
	private String name;
//creating a relationship with Employee One to many
//in one department there will be many employees
	@OneToMany
	private List<Employee> employees;
	public Department()
	{
		this.id=0;
		this.name=null;
		this.employees=null;
	}

}
