package com.hillel.dao.impl.hibernate;

import com.google.common.collect.ImmutableList;
import com.hillel.dao.BookDao;
import com.hillel.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class BookDaoImpl extends BasicCRUDImpl<Book> implements BookDao {

    @Autowired
    private SessionFactory sessionFactory;

    @PostConstruct
    private void initPersistableClass() {
        setPersistable(Book.class);
    }

    @Override
    public Optional<Book> getEagerStateById(Long id) {
        Session session = sessionFactory.openSession();
        session.enableFetchProfile("book_full_info");
        Book result = (Book) session.get(Book.class, id);
//        session.close();
        return Optional.of(result);
    }

    @Override
    public List<Book> getByTitle(String title) {
        Session session = sessionFactory.openSession();
        List<Book> result = session.createCriteria(Book.class).add(Restrictions.eq("title", title)).list();
        session.close();
        return ImmutableList.copyOf(result);
    }

}
