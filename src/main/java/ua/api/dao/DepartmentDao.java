package ua.api.dao;

import lombok.SneakyThrows;
import ua.api.exceptions.DepartmentDaoException;
import ua.api.exceptions.DepartmentNotAddedException;
import ua.api.exceptions.DepartmentNotDeletedException;
import ua.api.exceptions.DepartmentNotUpdatedException;
import ua.api.model.Department;
import ua.simpleservletframework.data.annotation.annotation.ComponentDao;
import ua.simpleservletframework.data.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.api.util.FieldsConstants.DEP_ID;
import static ua.api.util.FieldsConstants.DEP_NAME;
import static ua.api.util.SQLQueries.*;

@ComponentDao("employeeDao")
public class DepartmentDao implements Dao<Department> {
    @Override
    @SneakyThrows
    public Department add(Department department) {
        try (
                Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(ADD_DEPARTMENT)
        ) {

            ps.setLong(1, department.getId());
            ps.setString(2, department.getName());
            ps.executeUpdate();
            return department;

        } catch (SQLException e) {
            throw new DepartmentNotAddedException(e.getMessage());
        }
    }

    @Override
    @SneakyThrows
    public Department update(Department department) {
        try (
                Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(UPDATE_DEPARTMENT_WHERE_ID)
        ) {

            ps.setString(1, department.getName());
            ps.setLong(2, department.getId());
            ps.executeUpdate();
            return department;

        } catch (SQLException e) {
            throw new DepartmentNotUpdatedException(e.getMessage());
        }
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        try (
                Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(DELETE_DEPARTMENT)
        ) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DepartmentNotDeletedException(e.getMessage());
        }
    }

    @Override
    @SneakyThrows
    public Department get(Long id) {
        Department department = null;

        try (
                Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(SELECT_FROM_DEPARTMENT_WHERE_ID)
        ) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                department = Department.builder()
                        .id(rs.getLong(DEP_ID))
                        .name(rs.getString(DEP_NAME))
                        .build();
            }
        } catch (SQLException e) {
            throw new DepartmentDaoException(e);
        }

        return department;
    }

    @Override
    @SneakyThrows
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();

        try (
                Connection connection = ConnectionFactory.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(SELECT_ALL_FROM_DEPARTMENT);

            while (rs.next()) {
                Department department = Department.builder()
                        .id(rs.getLong(DEP_ID))
                        .name(rs.getString(DEP_NAME))
                        .build();
                departments.add(department);
            }
        } catch (SQLException e) {
            throw new DepartmentDaoException(e);
        }

        return departments;
    }
}
