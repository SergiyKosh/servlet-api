package ua.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import ua.api.exceptions.DepartmentBusinessException;
import ua.api.model.dao.DepartmentDatabaseDao;
import ua.api.model.entity.Department;
import ua.simpleservletframework.core.annotation.Autowired;
import ua.simpleservletframework.core.annotation.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Service("departmentService")
public class DepartmentService {
    @Autowired
    private DepartmentDatabaseDao departmentDao;

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

    @SneakyThrows
    public void update(Department department) {
//        try {
//            Department department = buildDepartment(request);
//
//            departmentDao.update(department);
//        } catch (IOException e) {
//            throw new DepartmentBusinessException(e);
//        }
//
//        Department department;
//
//        department = new ObjectMapper().readValue(request.getInputStream(), Department.class);
        departmentDao.update(department);
    }

    public void delete(String idStr) throws IOException {
        long id = Long.parseLong(idStr);
        departmentDao.delete(id);
    }

    public Department get(String idStr) {
            long id = Long.parseLong(idStr);
            return departmentDao.get(id);
    }

    public List<Department> findAll() {
            return departmentDao.findAll();
    }
}
