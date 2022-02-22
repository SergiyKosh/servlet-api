package ua.api.service;

import lombok.SneakyThrows;
import ua.api.dao.DepartmentDao;
import ua.api.exceptions.DepartmentNotAddedException;
import ua.api.exceptions.DepartmentNotFoundException;
import ua.api.exceptions.DepartmentNotUpdatedException;
import ua.api.model.Department;
import ua.simpleservletframework.core.annotation.Autowired;
import ua.simpleservletframework.core.annotation.Service;
import ua.simpleservletframework.mvc.servlet.DispatcherServlet;

import java.util.List;
import java.util.Optional;

@Service("departmentService")
public class DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;

    @SneakyThrows
    public Department add(Department department) {
        return Optional.ofNullable(departmentDao.add(department))
                .orElseThrow(DepartmentNotAddedException::new);
    }

    @SneakyThrows
    public Department update(Department department) {
        return Optional.ofNullable(departmentDao.update(department))
                .orElseThrow(DepartmentNotUpdatedException::new);
    }

    @SneakyThrows
    public Integer delete(String idStr) {
        long id = Long.parseLong(idStr);
        departmentDao.delete(id);
        return DispatcherServlet.response.getStatus();
    }

    public Department get(String idStr) {
        long id = Long.parseLong(idStr);
        return Optional.ofNullable(departmentDao.get(id))
                .orElseThrow(() -> new DepartmentNotFoundException(idStr));
    }

    public List<Department> findAll() {
        return departmentDao.findAll();
    }
}
