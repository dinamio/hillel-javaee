package com.hillel.model;

public class Book {

    private String bookName;
    private String authorName;
    private String  addedName;

    public Book() {
    }

    public Book(String bookName, String authorName, String  addedName) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.addedName = addedName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAddedName() {
        return addedName;
    }

    public void setAddedName(String addedName) {
        this.addedName = addedName;
    }

    @Override
    public String toString() {
        return bookName + ", " + authorName;
    }
}
