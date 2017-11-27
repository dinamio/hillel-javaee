package com.hillel.dao.impl.hibernate;

import com.google.common.collect.ImmutableList;
import com.hillel.dao.BasicCRUD;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Repository
abstract class BasicCRUDImpl<T> implements BasicCRUD<T> {

    @Setter
    private Class<T> persistable;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<T> getAll() {
        Session session = sessionFactory.openSession();
        List<T> result = session.createCriteria(persistable).list();
//        session.close();
        return ImmutableList.copyOf(result);
    }

    @Override
    public Optional<T> getById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        Session session = sessionFactory.openSession();
        T result = (T) session.get(persistable, id);
//        session.close();
        return Optional.ofNullable(result);
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        T result = (T) session.get(persistable, id);
        session.delete(result);
        session.flush();
        transaction.commit();
        session.close();
    }

    @Override
    public Long insert(T object) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long result = (Long) session.save(object);
        session.flush();
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public List<T> getByParams(Map<String, Object> params) {
        Session session = sessionFactory.openSession();
        List<T> result = session.createCriteria(persistable).add(Restrictions.allEq(params)).list();
        session.close();
        return ImmutableList.copyOf(result);
    }

    @Override
    public Optional<T> update(Long id, Consumer<T> updater) {
        if (id == null) {
            return Optional.empty();
        }
        Session session = sessionFactory.openSession();
        T element = (T) session.get(persistable, id);
        session.close();
        baseUpdate(element, updater);
        return Optional.ofNullable(element);
    }

    @Override
    public void update(T entity, Consumer<T> updater) {
        baseUpdate(entity, updater);
    }

    private void baseUpdate(T entity, Consumer<T> updater) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        updater.accept(entity);
        session.update(entity);
        transaction.commit();
        session.close();
    }
}
