package com.hillel.dao.impl.hibernate;

import com.google.common.collect.ImmutableList;
import com.hillel.dao.BasicCRUD;
import com.hillel.repository.HibernateUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
abstract class BasicCRUDImpl<T> implements BasicCRUD<T> {

    private final Class<T> persistable;

    protected BasicCRUDImpl(Class<T> persistable) {
        this.persistable = persistable;
    }

    @Override
    public List<T> getAll() {
        log.info("GET ALL {}", persistable);
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<T> result = session.createCriteria(persistable).list();
//        session.close();
        return ImmutableList.copyOf(result);
    }

    @Override
    public Optional<T> getById(Long id) {
        log.info("GET BY ID {} ,{}", id, persistable);
        Session session = HibernateUtils.getSessionFactory().openSession();
        T result = (T) session.get(persistable, id);
//        session.close();
        return Optional.ofNullable(result);
    }

    @Override
    public void deleteById(Long id) {
        log.info("DELETE BY ID {} ,{}", id, persistable);
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        T result = (T) session.get(persistable, id);
        session.delete(result);
        session.flush();
        transaction.commit();
        session.close();
    }

    @Override
    public Long insert(T object) {
        log.info("INSERT {}, {}", object, persistable);
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Long result = (Long) session.save(object);
        session.flush();
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public List<T> getByParams(Map<String, Object> params) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<T> result = session.createCriteria(persistable).add(Restrictions.allEq(params)).list();
        session.close();
        return ImmutableList.copyOf(result);
    }

    @Override
    public void update(Long id, Consumer<T> updater) {
        Optional<T> element = getById(id);
        element.ifPresent(el -> baseUpdate(el, updater));
    }

    @Override
    public void update(T entity, Consumer<T> updater) {
        baseUpdate(entity, updater);
    }

    private void baseUpdate(T entity, Consumer<T> updater) {
        log.info("UPDATE {}, {}", entity, persistable);
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        updater.accept(entity);
        session.flush();
        session.update(entity);
        transaction.commit();
        session.close();
    }
}
