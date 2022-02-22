package ua.api.service;

import jakarta.servlet.http.HttpServletRequest;
import ua.api.exceptions.DepartmentBusinessException;
import ua.api.exceptions.DepartmentDaoException;
import ua.api.model.dao.Dao;
import ua.api.model.dao.DepartmentDatabaseDao;
import ua.api.model.entity.Department;
import ua.simpleservletframework.data.annotation.annotation.ComponentDao;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static ua.api.util.FieldsConst.*;


@ComponentDao
public class DepartmentService {
    private final Dao<Department> departmentDao;

    public DepartmentService() {
        this.departmentDao = new DepartmentDatabaseDao();
    }

    private Department buildDepartment(HttpServletRequest request) throws IOException {
        int counter;
        StringBuilder str = new StringBuilder();

        while ((counter = request.getInputStream().read()) != -1) {
            str.append((char) counter);
        }

        String[] params = str.toString().split("&");
        Object[] paramsObj = Arrays.stream(params)
                .map(s -> s.replaceAll("id=", "")
                        .replaceAll("name=", "")
                ).toArray();
        params = Arrays.copyOf(paramsObj, paramsObj.length, String[].class);
        long id = Long.parseLong(params[0]);

        return Department.builder()
                .id(id)
                .name(params[1])
                .build();
    }

    public void add(HttpServletRequest request) throws DepartmentBusinessException {
        try {
            Department department = buildDepartment(request);
            departmentDao.add(department);
        } catch (IOException e) {
            throw new DepartmentBusinessException(e);
        }
    }

    public void update(HttpServletRequest request) throws DepartmentBusinessException {
        try {
            Department department = buildDepartment(request);

            departmentDao.update(department);
        } catch (IOException e) {
            throw new DepartmentBusinessException(e);
        }
    }

    public void delete(HttpServletRequest request) throws IOException {
        int counter;
        StringBuilder str = new StringBuilder();
        while ((counter = request.getInputStream().read()) != -1) {
            str.append((char) counter);
        }

        String[] params = str.toString().split("&");

        long id = Long.parseLong(params[0].replaceAll("id=", ""));

        departmentDao.delete(id);
    }

    public Department get(HttpServletRequest request) {
            long id = Long.parseLong(request.getParameter(DEP_ID));
            return departmentDao.get(id);
    }

    public List<Department> findAll() {
            return departmentDao.findAll();
    }
}
