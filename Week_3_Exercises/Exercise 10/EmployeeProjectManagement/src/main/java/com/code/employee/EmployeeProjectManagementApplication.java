package com.code.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.code.employee.audit.AuditorAwareImpl;

@SpringBootApplication
@Configuration
//@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EntityScan(basePackages = {"com.code.employee.entity", "com.code.department.entity"})
//@EnableJpaRepositories(basePackages = {"com.code.employee.repositories","com.code.department.repositories"})
public class EmployeeProjectManagementApplication {

	 @Bean
	    public AuditorAware<String> auditorAware() {
	        return new AuditorAwareImpl();
	    }
	public static void main(String[] args) {
		SpringApplication.run(EmployeeProjectManagementApplication.class, args);
	}
	

}
