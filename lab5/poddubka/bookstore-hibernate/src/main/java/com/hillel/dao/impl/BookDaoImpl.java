package com.hillel.dao.impl;

import com.hillel.dao.BookDao;
import com.hillel.model.Book;
import com.hillel.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

import static com.hillel.servlets.AddServlet.book;

public class BookDaoImpl implements BookDao {

    Session session = HibernateUtil.getSession();
    Transaction transaction = session.getTransaction();

    @Override
    public List<Book> getAllBook() {

        List<Book> list = session.createQuery("from Book").list();
        return list;
    }

    @Override
    public Integer insertBook(Book book) {

        Integer id  = (Integer) session.save(book);
        transaction.commit();
        return id;
    }

    @Override
    public Book findBookById(Integer id) {

        Book book = (Book) session.get(Book.class, id);
        transaction.commit();
        return book;
    }

    @Override
    public void deleteBookById(Integer id) {
        session.createQuery("delete from 'Book' where id=" + id + ";").list();
        // или ???
        //session.delete("from Book", book.getId());
    }
}
