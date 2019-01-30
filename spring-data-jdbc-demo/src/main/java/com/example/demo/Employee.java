package com.example.demo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@EqualsAndHashCode
@ToString
@Getter
public final class Employee {

    @Id
    private final Long id;
    private final String firstName;
    private final String lastName;

    public static Employee of(String firstName, String lastName) {
        return new Employee(null, firstName, lastName);
    }

    private Employee(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee withId(Long id) {
        return new Employee(id, firstName, lastName);
    }

    public Employee withFirstName(String firstName) {
        return new Employee(id, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
