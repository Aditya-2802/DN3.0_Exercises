package com.code.employee.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(
	    basePackages = "com.code.department.repositories",  // Secondary Repository package
	    entityManagerFactoryRef = "secondaryEntityManagerFactory",
	    transactionManagerRef = "secondaryTransactionManager"
	)

public class SecondaryJpaConfig {
	@Bean(name = "secondaryEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
	        EntityManagerFactoryBuilder builder, @Qualifier("secondaryDataSource") DataSource dataSource) {
	    return builder
	            .dataSource(dataSource)
	            .packages("com.code.department.entity")  // Package with Department entities
	            .persistenceUnit("secondary")
	           // .properties(hibernateProperties("org.hibernate.dialect.MySQL8Dialect"))
	            .build();
	}
	private Map<String, Object> hibernateProperties(String dialect) {
	    Map<String, Object> props = new HashMap<>();
	    props.put("hibernate.dialect", dialect);
	    return props;
	}
	@Bean(name = "secondaryTransactionManager")
	public PlatformTransactionManager secondaryTransactionManager(
	        @Qualifier("secondaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
	    return new JpaTransactionManager(entityManagerFactory);
	}
}