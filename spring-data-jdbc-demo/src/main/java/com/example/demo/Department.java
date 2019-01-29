package com.example.demo;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Set;

@EqualsAndHashCode
@ToString
public class Department {

    @Id
    private final Long departmentId;

    private final String name;

    private Set<Employee> employees;

    public static Department of(String name) {
        return new Department(null, name);
    }

    private Department(Long departmentId, String name) {
        this.departmentId = departmentId;
        this.name = name;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Department withDepartmentId(Long departmentId) {
        return new Department(departmentId, name);
    }

    public Department withName(String name) {
        return new Department(departmentId, name);
    }

}
