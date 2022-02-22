package ua.api.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import ua.api.exceptions.EmployeeBusinessException;
import ua.api.model.dao.EmployeeDatabaseDao;
import ua.api.model.entity.Employee;
import ua.simpleservletframework.core.annotation.Autowired;
import ua.simpleservletframework.core.annotation.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static ua.simpleservletframework.mvc.servlet.DispatcherServlet.*;

@Service("employeeService")
public class EmployeeService {
    @Autowired
    private EmployeeDatabaseDao employeeDao;

    private String[] getParams(HttpServletRequest request) throws IOException {
        int counter;
        StringBuilder str = new StringBuilder();

        while ((counter = request.getInputStream().read()) != -1) {
            str.append((char) counter);
        }

        return str.toString().split("&");
    }

    @SneakyThrows
    public void add() {
        try {
            String[] params = getParams(request);
            Object[] paramsObj = Arrays.stream(params)
                    .map(s -> s.replaceAll("name=", "")
                            .replaceAll("salary=", "")
                            .replaceAll("chiefId=", "")
                            .replaceAll("departmentId=", "")
                            .replaceAll("%20", " ")
                    ).toArray();
            params = Arrays.copyOf(paramsObj, paramsObj.length, String[].class);
            String name = params[0];
            Integer salary = Integer.parseInt(params[1]);
            long chiefId = Long.parseLong(params[2]);
            long departmentId = Long.parseLong(params[3]);

            Employee employee = Employee.builder()
                    .name(name)
                    .salary(salary)
                    .departmentId(departmentId)
                    .chiefId(chiefId)
                    .build();
            employeeDao.add(employee);
        } catch (IOException e) {
            throw new EmployeeBusinessException(e);
        }
    }

    @SneakyThrows
    public void update(String idStr) {
        try {
            int counter;
            StringBuilder str = new StringBuilder();

            while ((counter = request.getInputStream().read()) != -1) {
                str.append((char) counter);
            }

            String[] params = str.toString().split("&");
            Object[] paramsObj = Arrays.stream(params)
                    .map(s -> s.replaceAll("id=", "")
                            .replaceAll("name=", "")
                            .replaceAll("salary=", "")
                            .replaceAll("chiefId=", "")
                            .replaceAll("departmentId=", "")
                    ).toArray();
            params = Arrays.copyOf(paramsObj, paramsObj.length, String[].class);

            long id = Long.parseLong(params[0]);
            long departmentId = Long.parseLong(params[1]);
            String name = params[2];
            Integer salary = Integer.parseInt(params[3]);
            long chiefId = Long.parseLong(params[4]);

            Employee employee = Employee.builder()
                    .id(id)
                    .name(name)
                    .salary(salary)
                    .departmentId(departmentId)
                    .chiefId(chiefId)
                    .build();
            employeeDao.update(employee);
        } catch (IOException e) {
            throw new EmployeeBusinessException(e);
        }
    }

    public void delete(String idStr) {
            long id = Long.parseLong(idStr);
            employeeDao.delete(id);
    }

    public Employee get(String idStr) {
        long id = Long.parseLong(idStr);
        return employeeDao.get(id);
    }

    public List<Employee> findAll() {
        return employeeDao.findAll();
    }
}
