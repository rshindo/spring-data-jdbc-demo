package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "classpath:insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:truncate.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class SpringDataJdbcDemoApplicationTests {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test1() {
        Employee employee = Employee.of("John", "Do");
//        employee.setFirstname("John");
//        employee.setLastname("Do");
//        employee.setAge(30);
//        employee.setHiredAt(LocalDate.of(2012, 4, 1));

        // Insert
        employee = employeeRepository.save(employee);
        Long employeeNumber = employee.getId();

        Optional<Employee> insertedOpt = employeeRepository.findById(employeeNumber);
        assertTrue(insertedOpt.isPresent());
        Employee inserted = insertedOpt.get();
        assertAll("insert",
                () -> assertEquals(inserted.getId(), employeeNumber),
                () -> assertEquals(inserted.getFirstName(), "John"),
                () -> assertEquals(inserted.getLastName(), "Do"));

        // Update
        Employee newEmployee = employee.withFirstName("Joe");
        employeeRepository.save(newEmployee);

        Optional<Employee> updatedOpt = employeeRepository.findById(employeeNumber);
        assertTrue(updatedOpt.isPresent());
        Employee updated = updatedOpt.get();
        assertEquals(updated.getFirstName(), "Joe");

        // Delete
        employeeRepository.delete(employee);

        Optional<Employee> deleted = employeeRepository.findById(employeeNumber);
        assertTrue(!deleted.isPresent());
    }

//    @Test
//    public void testFindByHiredAtRange() {
//        LocalDate since = LocalDate.of(2015, 4, 1);
//        LocalDate until = LocalDate.now();
//
//        List<Employee> result = employeeRepository.findByHiredAtRange(since, until);
//
//        assertEquals(1, result.size());
//        Employee employee = result.get(0);
//        System.out.println(employee);
//        assertAll("Mya-mori",
//                () -> assertEquals(employee.getFirstName(), "Aoi"),
//                () -> assertEquals(employee.getLastName(), "Miyamori"));
//    }

//    @Test
//    void testDepartmentRepository() {
//        Optional<Department> maybeDepartment = departmentRepository.findById(1L);
//        assertTrue(maybeDepartment.isPresent());
//        maybeDepartment.ifPresentOrElse(department -> {
//            System.out.println(department);
//            Set<Employee> employees = department.getEmployees();
//            assertEquals(1, employees.size());
//            employees.forEach(employee -> assertEquals("Miyamori", employee.getLastName()));
//        }, () -> fail("department not found."));
//    }

    @Test
    void testDepartmentRepository_deleteAll() {
        departmentRepository.deleteAll();
        Iterable<Employee> iterable = employeeRepository.findAll();
        int count = 0;
        for (Employee employee : iterable) {
            count++;
            System.out.println(employee);
        }
        assertEquals(0, count);
    }

}
