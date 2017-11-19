package com.hillel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Items {

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "author_id")
    private Integer authorId;

    public Items() {
    }

    public Items(Integer bookId, Integer authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}
