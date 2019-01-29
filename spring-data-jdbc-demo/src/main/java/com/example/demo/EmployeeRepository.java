package com.example.demo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("select * from employee where :since <= hired_at and hired_at <= :until")
    List<Employee> findByHiredAtRange(@Param("since") LocalDate since, @Param("until") LocalDate until);

    List<Employee> findByAge(int age);
}
