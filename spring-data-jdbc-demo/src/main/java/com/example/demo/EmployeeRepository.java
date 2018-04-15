package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("select * from employee where :since <= hired_at and hired_at <= :until")
    List<Employee> findByHiredAtRange(@Param("since") LocalDate since, @Param("until") LocalDate until);
}
