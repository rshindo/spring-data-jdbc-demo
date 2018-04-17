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
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class SpringDataJdbcDemoApplicationTests {

    @Autowired
    EmployeeRepository repo;
    
    @Autowired
    EmployeeService service;

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

//    @Test
//    public void testFindByHiredAtRange() {
//        LocalDate since = LocalDate.of(2015, 4, 1);
//        LocalDate until = LocalDate.now();
//
//        List<Employee> result = repo.findByHiredAtRange(since, until);
//
//        assertEquals(1, result.size());
//        Employee employee = result.get(0);
//        assertAll("Mya-mori",
//                () -> assertEquals(employee.getFirstname(), "Aoi"),
//                () -> assertEquals(employee.getLastname(), "Miyamori"));
//    }
    
    @Test
    public void testForHibernate() {
        
        Optional<Employee> maybeEmployee = repo.findById(1L);
        assertTrue(maybeEmployee.isPresent());
        Employee employee = maybeEmployee.get();
        assertAll("Mya-mori",
                () -> assertEquals(employee.getFirstname(), "Aoi"),
                () -> assertEquals(employee.getLastname(), "Miyamori"),
                () -> assertEquals(employee.getAge(), Integer.valueOf(21)));
        
        Employee newEmployee = new Employee();
        newEmployee.setFirstname("Midori");
        newEmployee.setLastname("Imai");
        newEmployee.setAge(18);
        newEmployee.setHiredAt(LocalDate.of(2018, 04, 01));
        service.save(newEmployee);
        
        Long employeeNumber = newEmployee.getEmployeeNumber();
        Optional<Employee> maybeNewEmployee = repo.findById(employeeNumber);
        assertTrue(maybeNewEmployee.isPresent());
        Employee newEmpResult = maybeNewEmployee.get();
        assertAll("Ri-chan",
                () -> assertEquals(newEmpResult.getFirstname(), "Midori"),
                () -> assertEquals(newEmpResult.getLastname(), "Imai"),
                () -> assertEquals(newEmpResult.getAge(), Integer.valueOf(18)));
        
    }
}