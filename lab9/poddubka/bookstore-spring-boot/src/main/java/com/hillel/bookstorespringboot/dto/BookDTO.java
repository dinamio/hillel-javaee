package com.hillel.bookstorespringboot.dto;

public class BookDTO {

    private String id;
    private String bookName;
    private String authors;
    private String userName;

    public BookDTO() {
    }

    public BookDTO(String id, String bookName, String authors, String userName) {
        this.id = id;
        this.bookName = bookName;
        this.authors = authors;
        this.userName = userName;
    }

    public BookDTO(String bookName, String authors) {
        this.bookName = bookName;
        this.authors = authors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
