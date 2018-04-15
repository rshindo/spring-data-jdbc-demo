package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.core.DataAccessStrategy;
import org.springframework.data.jdbc.core.DefaultDataAccessStrategy;
import org.springframework.data.jdbc.core.SqlGeneratorSource;
import org.springframework.data.jdbc.mapping.model.DelimiterNamingStrategy;
import org.springframework.data.jdbc.mapping.model.JdbcMappingContext;
import org.springframework.data.jdbc.mapping.model.NamingStrategy;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;


@SpringBootApplication
@EnableJdbcRepositories
public class SpringDataJdbcDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJdbcDemoApplication.class, args);
	}

	@Autowired
	JdbcMappingContext context;
	@Autowired
	DataSource datasource;

	@Bean
	DataAccessStrategy dataAccessStrategy() {
	    return new DefaultDataAccessStrategy(
	            new SqlGeneratorSource(context),
	            context);
	}

	@Bean
	NamingStrategy namingStrategy() {
//	    return new CustomNamingStrategy();
	    return new DelimiterNamingStrategy();
	}
}
