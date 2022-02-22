package ua.api.dao;

import java.util.List;

public interface Dao<T> {
    T add(T entity);

    T update(T entity);

    void delete(Long id);

    T get(Long id);

    List<T> findAll();
}
