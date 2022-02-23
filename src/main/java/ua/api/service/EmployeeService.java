package ua.api.service;

import lombok.SneakyThrows;
import ua.api.dao.Dao;
import ua.api.exceptions.EmployeeNotAddedException;
import ua.api.exceptions.EmployeeNotFoundException;
import ua.api.exceptions.EmployeeNotUpdatedException;
import ua.api.model.Employee;
import ua.simpleservletframework.core.annotation.Autowired;
import ua.simpleservletframework.core.annotation.Service;
import ua.simpleservletframework.mvc.servlet.DispatcherServlet;

import java.util.List;
import java.util.Optional;

@Service("employeeService")
public class EmployeeService implements ua.api.service.Service<Employee> {
    @Autowired("employeeDao")
    private Dao<Employee> employeeDao;

    @SneakyThrows
    public Employee add(Employee employeeBody) {
        return Optional.ofNullable(employeeDao.add(employeeBody))
                .orElseThrow(EmployeeNotAddedException::new);
    }

    @SneakyThrows
    public Employee update(Employee employeeBody) {
        return Optional.ofNullable(employeeDao.update(employeeBody))
                .orElseThrow(EmployeeNotUpdatedException::new);
    }

    public Integer delete(String idStr) {
        long id = Long.parseLong(idStr);
        employeeDao.delete(id);
        return DispatcherServlet.response.getStatus();
    }

    public Employee get(String idStr) {
        long id = Long.parseLong(idStr);
        return Optional.ofNullable(employeeDao.get(id))
                .orElseThrow(() -> new EmployeeNotFoundException(idStr));
    }

    public List<Employee> findAll() {
        return employeeDao.findAll();
    }
}
