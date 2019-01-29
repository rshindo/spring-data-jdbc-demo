package com.example.demo;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;

@EqualsAndHashCode
@ToString
public final class Employee {

    @Id
    private final Long employeeNumber;
    private final String firstname;
    private final String lastname;
    private final Integer age;
    private final LocalDate hiredAt;
    @Column(value = "department", keyColumn = "department_id")
    private Department department;

    public static Employee of(String firstname, String lastname, Integer age, LocalDate hiredAt) {
        return new Employee(null, firstname, lastname, age, hiredAt, null);
    }

    private Employee(Long employeeNumber, String firstname, String lastname, Integer age, LocalDate hiredAt, Department department) {
        this.employeeNumber = employeeNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.hiredAt = hiredAt;
    }

    public Employee withEmployeeNumber(Long employeeNumber) {
        return new Employee(employeeNumber, firstname, lastname, age, hiredAt, department);
    }

    public Employee withFirstname(String firstname) {
        return new Employee(employeeNumber, firstname, lastname, age, hiredAt, department);
    }

    public Employee withLastname(String lastname) {
        return new Employee(employeeNumber, firstname, lastname, age, hiredAt, department);
    }

    public Employee withAge(Integer age) {
        return new Employee(employeeNumber, firstname, lastname, age, hiredAt, department);
    }

    public Employee withHiredAt(LocalDate hiredAt) {
        return new Employee(employeeNumber, firstname, lastname, age, hiredAt, department);
    }

    public Long getEmployeeNumber() {
        return employeeNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Integer getAge() {
        return age;
    }

    public LocalDate getHiredAt() {
        return hiredAt;
    }

    public Department getDepartment() {
        return department;
    }
}
