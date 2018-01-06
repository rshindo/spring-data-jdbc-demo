package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SpringDataJdbcDemoApplicationTests {

    @Autowired
    EmployeeRepository repo;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test1() {
        Employee employee = new Employee();
        employee.setFirstname("John");
        employee.setLastname("Do");
        employee.setAge(30);
        employee.setHiredAt(LocalDate.of(2012, 4, 1));

        // Insert
        employee = repo.save(employee);
        Long employeeNumber = employee.getEmployeeNumber();

        Optional<Employee> insertedOpt = repo.findById(employeeNumber);
        assertTrue(insertedOpt.isPresent());
        Employee inserted = insertedOpt.get();
        assertAll("insert",
                () -> assertEquals(inserted.getEmployeeNumber(), employeeNumber),
                () -> assertEquals(inserted.getFirstname(), "John"),
                () -> assertEquals(inserted.getLastname(), "Do"),
                () -> assertEquals(inserted.getAge(), Integer.valueOf(30)),
                () -> assertEquals(inserted.getHiredAt(), LocalDate.of(2012, 4, 1)));

        // Update
        employee.setAge(31);
        repo.save(employee);

        Optional<Employee> updatedOpt = repo.findById(employeeNumber);
        assertTrue(updatedOpt.isPresent());
        Employee updated = updatedOpt.get();
        assertEquals(updated.getAge(), Integer.valueOf(31));

        // Delete
        repo.delete(employee);

        Optional<Employee> deleted = repo.findById(employeeNumber);
        assertTrue(!deleted.isPresent());
    }
}