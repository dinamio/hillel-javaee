package dao;

import java.util.List;

/**
 * Created by eugen on 11/21/17.
 */
public interface CRUDDao<T> {

    void save(T t);

    List<T> findAll();

    T findById(Integer id);

    void delete(T t);

    void update(T t);
}
