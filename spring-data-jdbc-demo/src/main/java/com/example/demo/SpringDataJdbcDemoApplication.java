package com.example.demo;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.core.DataAccessStrategy;
import org.springframework.data.jdbc.mapping.model.DelimiterNamingStrategy;
import org.springframework.data.jdbc.mapping.model.JdbcMappingContext;
import org.springframework.data.jdbc.mapping.model.NamingStrategy;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import framework.jpa.HibernateDataAcccessStrategy;

@SpringBootApplication
@EnableJdbcRepositories
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
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
        return new HibernateDataAcccessStrategy(localSessionFactoryBean().getObject());
    }

    @Bean
    NamingStrategy namingStrategy() {
        //	    return new CustomNamingStrategy();
        return new DelimiterNamingStrategy();
    }

    @Bean
    LocalSessionFactoryBean localSessionFactoryBean() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(datasource);
        localSessionFactoryBean.setAnnotatedClasses(Employee.class);
        Properties props = new Properties();
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        localSessionFactoryBean.setHibernateProperties(props);
        return localSessionFactoryBean;
    }

    @Bean
    HibernateTransactionManager transactionManager(LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory.getObject());
        return txManager;
    }

}
