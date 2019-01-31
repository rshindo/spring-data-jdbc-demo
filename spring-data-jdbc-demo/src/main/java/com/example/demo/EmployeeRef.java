package com.example.demo;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode
@ToString
@Table("department_employee")
public class EmployeeRef {
    private Long employee;

    public EmployeeRef(Long employee) {
        this.employee = employee;
    }

    public Long getEmployee() {
        return employee;
    }
}
