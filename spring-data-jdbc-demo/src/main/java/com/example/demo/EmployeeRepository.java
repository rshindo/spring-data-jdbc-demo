package com.example.demo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long>, EmployeeRepositoryCustom {

    @Query("SELECT * FROM employee WHERE firstName = :firstName")
    List<Employee> findByFirstName(@Param("firstName") String firstName);
}
