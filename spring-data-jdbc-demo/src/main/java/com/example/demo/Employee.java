package com.example.demo;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Employee {

    @Id
    private Long employeeNumber;
    private String firstname;
    private String lastname;
    private Integer age;
    private LocalDate hiredAt;

}
