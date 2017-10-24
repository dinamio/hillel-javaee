package com.hillel;

public class Book {

    private int id;
    private String book;
    private String author;

    public Book() {
    }

    public Book(int id, String book, String author) {
        this.id = id;
        this.book = book;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return id + ", " + book + ", " + author +"\n";
    }

}
