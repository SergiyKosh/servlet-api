package ua.api.model.dao;

import ua.api.exceptions.DepartmentDaoException;
import ua.api.exceptions.EmployeeDaoException;
import ua.api.model.entity.Department;

import java.util.List;

public interface Dao<T> {
    void add(T entity);

    void update(T entity);

    void delete(Long id);

    T get(Long id);

    List<T> findAll();
}
