package ua.api.dao;

import lombok.SneakyThrows;
import ua.api.exceptions.EmployeeDaoException;
import ua.api.exceptions.EmployeeNotAddedException;
import ua.api.exceptions.EmployeeNotDeletedException;
import ua.api.exceptions.EmployeeNotUpdatedException;
import ua.api.model.Employee;
import ua.simpleservletframework.data.annotation.annotation.ComponentDao;
import ua.simpleservletframework.data.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ua.api.util.FieldsConst.*;
import static ua.api.util.SQLQueries.*;

@ComponentDao("departmentDao")
public class EmployeeDao implements Dao<Employee> {
    @Override
    @SneakyThrows
    public Employee add(Employee employee) {
        try (
                Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(ADD_EMPLOYEE)
        ) {

            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getSalary());
            ps.setLong(3, employee.getDepartmentId());

            if (Objects.isNull(employee.getChiefId())) {
                ps.setNull(4, Types.NULL);
            } else {
                ps.setLong(4, employee.getChiefId());
            }

            ps.executeUpdate();
            return employee;

        } catch (SQLException e) {
            throw new EmployeeNotAddedException(e.getMessage());
        }
    }

    @Override
    @SneakyThrows
    public Employee update(Employee employee) {
        try (
                Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE_WHERE_ID)
        ) {
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getSalary());
            ps.setLong(3, employee.getDepartmentId());
            if (Objects.isNull(employee.getChiefId())) {
                ps.setLong(4, Types.NULL);
            } else {
                ps.setLong(4, employee.getChiefId());
            }
            ps.setLong(5, employee.getId());
            ps.executeUpdate();
            return employee;

        } catch (SQLException e) {
            throw new EmployeeNotUpdatedException(e.getMessage());
        }
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        try (
                Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE)
        ) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new EmployeeNotDeletedException(e.getMessage());
        }
    }

    @Override
    @SneakyThrows
    public Employee get(Long id) {
        Employee employee = null;

        try (
                Connection connection = ConnectionFactory.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(SELECT_FROM_EMPLOYEE_WHERE_ID)
        ) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                employee = Employee.builder()
                        .id(rs.getLong(EMPLOYEE_ID))
                        .name(rs.getString(NAME))
                        .salary(rs.getInt(SALARY))
                        .departmentId(rs.getLong(DEPARTMENT_ID))
                        .chiefId(rs.getLong(CHIEF_ID))
                        .build();
            }

        } catch (SQLException e) {
            throw new EmployeeDaoException(e);
        }

        return employee;
    }

    @Override
    @SneakyThrows
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();

        try (
                Connection connection = ConnectionFactory.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(SELECT_FROM_EMPLOYEE);

            while (rs.next()) {
                Employee employee = Employee.builder()
                        .id(rs.getLong(EMPLOYEE_ID))
                        .name(rs.getString(NAME))
                        .salary(rs.getInt(SALARY))
                        .departmentId(rs.getLong(DEPARTMENT_ID))
                        .chiefId(rs.getLong(CHIEF_ID))
                        .build();
                employees.add(employee);
            }

        } catch (SQLException e) {
            throw new EmployeeDaoException(e);
        }

        return employees;
    }
}
