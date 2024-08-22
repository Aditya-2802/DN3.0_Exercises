package com.code.employee.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.code.employee.repositories", // Primary Repository package
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager"
)

public class JpaConfig {


@Primary
@Bean(name = "primaryEntityManagerFactory")

//creating the method primaryEntityManagerFactory returns the object of
//LocalContainerEntityManagerFactoryBean according to JPA's standard container bootstrap contract.
//This is the most powerful way to setup a shared JPA EntityManagerFactory in a Spring application
// context; the EntityManagerFactory can then be passed to JPA based DAOs (DATA ACCESS OBJECT) via dependency injection.
//Or to a LocalEntityManagerFacotoryBean definition is just a matter of configuration!

public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
        EntityManagerFactoryBuilder builder, @Qualifier("primaryDataSource") DataSource dataSource) {
    return builder
            .dataSource(dataSource)
            .packages("com.code.employee.entity")  // Package with Employee entities
            .persistenceUnit("primary")
           .properties(hibernateProperties("org.hibernate.dialect.H2Dialect"))
            .build();
}
private Map<String, Object> hibernateProperties(String dialect) {
    Map<String, Object> props = new HashMap<>();
    props.put("hibernate.dialect", dialect);
    return props;
}


@Primary
@Bean(name = "primaryTransactionManager")
public PlatformTransactionManager primaryTransactionManager(
        @Qualifier("primaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
}


}
