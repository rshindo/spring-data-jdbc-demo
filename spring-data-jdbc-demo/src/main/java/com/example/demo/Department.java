package com.example.demo;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@ToString
public class Department {

    @Id
    private final Long id;

    private final String name;

    private Set<EmployeeRef> employees = new HashSet<>();

    public static Department of(String name) {
        return new Department(null, name);
    }

    private Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<EmployeeRef> getEmployees() {
        return employees;
    }

    public Department withId(Long departmentId) {
        return new Department(departmentId, name);
    }

    public void addEmployee(Employee employee) {
        employees.add(new EmployeeRef(employee.getId()));
    }

}
