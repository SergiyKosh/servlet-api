package ua.api.model.dao;

import java.util.List;

public interface Dao<T> {
    void add(T entity);

    void update(T entity);

    void delete(Long id);

    T get(Long id);

    List<T> findAll();
}
