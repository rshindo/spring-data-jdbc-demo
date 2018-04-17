package com.example.demo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.core.DataAccessStrategy;
import org.springframework.data.jdbc.mapping.model.DelimiterNamingStrategy;
import org.springframework.data.jdbc.mapping.model.NamingStrategy;
import org.springframework.data.jdbc.mybatis.MyBatisDataAccessStrategy;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
public class SpringDataJdbcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJdbcDemoApplication.class, args);
    }

    @Bean
    DataAccessStrategy dataAccessStrategy(SqlSession sqlSession) {
//        return new DefaultDataAccessStrategy(
//                new SqlGeneratorSource(context),
//                context);
        return new MyBatisDataAccessStrategy(sqlSession);
    }

    @Bean
    NamingStrategy namingStrategy() {
        //	    return new CustomNamingStrategy();
        return new DelimiterNamingStrategy();
    }
}
