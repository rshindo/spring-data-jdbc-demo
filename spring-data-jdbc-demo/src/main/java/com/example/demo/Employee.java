package com.example.demo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Entity
@Data
public class Employee {

    @javax.persistence.Id
    @Id
    @Column(name="employee_number")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long employeeNumber;
    
    @Column(name="firstname")
    private String firstname;
    
    @Column(name="lastname")
    private String lastname;
    
    @Column(name="age")
    private Integer age;

//    @Temporal(TemporalType.DATE)
    @Column(name="hired_at")
    private LocalDate hiredAt;

}
