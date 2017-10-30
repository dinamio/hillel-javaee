package com.hillel;

import java.util.List;

public class BookList {

    private  List<Book> booklist;

    public BookList(List<Book> booklist) {
        this.booklist = booklist;
    }

    public List<Book> getBooklist() {
        return booklist;
    }

    public void setBooklist(List<Book> booklist) {
        this.booklist = booklist;
    }
}
