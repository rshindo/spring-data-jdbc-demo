package com.example.demo;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@EqualsAndHashCode
@ToString
public class Author {
    @Id
    private Long id;
    private String name;

    public static Author of(String name) {
        return new Author(null, name);
    }

    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Author withId(Long id) {
        return new Author(id, name);
    }
}
