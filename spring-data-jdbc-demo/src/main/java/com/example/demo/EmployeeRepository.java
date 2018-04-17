package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

//    @Query("select * from employee where :since <= hired_at and hired_at <= :until")
//    List<Employee> findByHiredAtRange(@Param("since") LocalDate since, @Param("until") LocalDate until);
}
