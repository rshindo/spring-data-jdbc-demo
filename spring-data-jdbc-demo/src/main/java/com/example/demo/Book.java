package com.example.demo;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@ToString
public class Book {
    @Id
    private Long id;
    private Set<AuthorRef> authors = new HashSet<>();
    private String title;

    public static Book of(String title) {
        return new Book(null, title);
    }

    private Book(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public void addAuthor(Author author) {
        authors.add(createAuthorRef(author));
    }

    private AuthorRef createAuthorRef(Author author) {

        Assert.notNull(author, "Author must not be null");
        Assert.notNull(author.getId(), "Author id, must not be null");

        AuthorRef authorRef = new AuthorRef(author.getId());
        //        authorRef.author = author.getId();
        return authorRef;
    }

    public Book withId(Long id) {
        Book book = new Book(id, title);
        book.authors = authors;
        return book;
    }
}
