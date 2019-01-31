package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DepartmentRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void test() {
        Employee employee1 = Employee.of("Freddie", "Mercury");
        Employee employee2 = Employee.of("Brian", "May");
        Iterable<Employee> savedEmployees = employeeRepository.saveAll(List.of(employee1, employee2));

        Department department = Department.of("Develop");
        savedEmployees.forEach(department::addEmployee);
        department = departmentRepository.save(department);

        System.out.println(jdbcTemplate.queryForList("SELECT * FROM employee"));
        System.out.println(jdbcTemplate.queryForList("SELECT * FROM department"));
        System.out.println(jdbcTemplate.queryForList("SELECT * FROM department_employee"));

        for (Department department1 : departmentRepository.findAll()) {
            System.out.println(department1);
        }

        for (Employee employee : employeeRepository.findAll()) {
            System.out.println(employee);
        }

        departmentRepository.delete(department);
        System.out.println(jdbcTemplate.queryForList("SELECT * FROM employee"));
        System.out.println(jdbcTemplate.queryForList("SELECT * FROM department"));
        System.out.println(jdbcTemplate.queryForList("SELECT * FROM department_employee"));

    }

    @Autowired
    AuthorRepository authors;
    @Autowired
    BookRepository books;

    @Test
    public void booksAndAuthors() {

        Author author = Author.of("Greg L. Turnquist");
        //        author.name = "Greg L. Turnquist";

        author = authors.save(author);

        Book book = Book.of("Spring Boot");
        //        book.title = "Spring Boot";
        book.addAuthor(author);

        books.save(book);

        System.out.println(jdbcTemplate.queryForList("SELECT * FROM book_author"));
        //        System.out.println(jdbcTemplate.queryForList("SELECT * FROM department"));

        books.deleteAll();

        assertEquals(1, authors.count());
    }

}
