package ua.api.model.dao;

import lombok.SneakyThrows;
import ua.api.exceptions.DepartmentDaoException;
import ua.api.model.entity.Department;
import ua.simpleservletframework.data.annotation.annotation.ComponentDao;
import ua.simpleservletframework.data.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.api.util.FieldsConst.DEP_ID;
import static ua.api.util.FieldsConst.DEP_NAME;
import static ua.api.util.SQLQueries.*;

@ComponentDao("employeeDao")
public class DepartmentDatabaseDao implements Dao<Department> {
    @Override
    @SneakyThrows
    public void add(Department department) {
        try (
                Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(ADD_DEPARTMENT)
        ) {

            ps.setLong(1, department.getId());
            ps.setString(2, department.getName());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DepartmentDaoException(e);
        }
    }

    @Override
    @SneakyThrows
    public void update(Department department) {
        try (
                Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(UPDATE_DEPARTMENT_WHERE_ID)
        ) {

            ps.setString(1, department.getName());
            ps.setLong(2, department.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DepartmentDaoException(e);
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
            throw new DepartmentDaoException(e);
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
