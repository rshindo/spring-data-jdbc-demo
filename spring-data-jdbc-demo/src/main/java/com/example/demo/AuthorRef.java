package com.example.demo;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

@EqualsAndHashCode
@ToString
@Table("Book_Author")
public class AuthorRef {

    private Long author;

    public AuthorRef(Long author) {
        this.author = author;
    }

    public Long getAuthor() {
        return author;
    }
}
