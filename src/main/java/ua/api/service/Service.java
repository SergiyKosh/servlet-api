package ua.api.service;

import java.util.List;

public interface Service<T> {
    T add(T entity);
    T update(T entity);
    Integer delete(String id);
    T get(String id);
    List<T> findAll();
}
