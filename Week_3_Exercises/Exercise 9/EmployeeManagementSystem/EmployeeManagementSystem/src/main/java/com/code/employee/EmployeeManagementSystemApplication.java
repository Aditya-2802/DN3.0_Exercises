package com.code.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.code.employee.audit.AuditorAwareImpl;

@SpringBootApplication
//@Configuration
//enable JPAAuditing
//@EnableJpaAuditing(auditorAwareRef="auditorAware")
@EntityScan(basePackages= {"com.code.employee.entity", "com.code.department.entity"})
public class EmployeeManagementSystemApplication {
	//create a bean of the AuditorAware
	@Bean
		public AuditorAware<String> auditorAware(){
		return new AuditorAwareImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}

}
