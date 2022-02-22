package ua.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.api.exceptions.EmployeeBusinessException;
import ua.api.model.entity.Employee;
import ua.api.service.EmployeeService;
import ua.simpleservletframework.mvc.annotation.annotation.controller.Controller;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.DeleteMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.GetMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.PostMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.PutMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeService service = new EmployeeService();

    @GetMapping("/employees")
    public List<Employee> findAll(HttpServletRequest request, HttpServletResponse response) throws EmployeeBusinessException {
        return service.findAll();
    }

    @GetMapping("/employee")
    public Employee get(HttpServletRequest request, HttpServletResponse response) throws EmployeeBusinessException {
        return service.get(request);
    }

    @PutMapping("/employees/new")
    public String save(HttpServletRequest request, HttpServletResponse response) throws EmployeeBusinessException {
        service.add(request);
        return "redirect:/http://localhost:8081/employees";
    }

    @PostMapping("/employee")
    public String update(HttpServletRequest request, HttpServletResponse response) throws EmployeeBusinessException {
        service.update(request);
        return "redirect:/http://localhost:8081/employees";
    }

    @DeleteMapping("/employee")
    public String delete(HttpServletRequest request, HttpServletResponse response) throws IOException, EmployeeBusinessException {
        service.delete(request);
        return "redirect:/http://localhost:8081/employees";
    }
}
