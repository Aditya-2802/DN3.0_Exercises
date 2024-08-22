package com.code.employee.projection;
import org.springframework.beans.factory.annotation.Value;
public interface EmployeeValueProjection {
	@Value("#{target.name}")
    String getName();

    @Value("#{target.email}")
    String getEmail();
}
