package ua.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.api.exceptions.DepartmentBusinessException;
import ua.api.model.entity.Department;
import ua.api.service.DepartmentService;
import ua.simpleservletframework.core.annotation.annotation.component.Autowired;
import ua.simpleservletframework.mvc.annotation.annotation.controller.Controller;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.DeleteMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.GetMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.PostMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.PutMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    @GetMapping("/departments")
    public List<Department> findAll(HttpServletRequest request, HttpServletResponse response) throws DepartmentBusinessException {
        return service.findAll();
    }

    @GetMapping("/department")
    public Department get(HttpServletRequest request, HttpServletResponse response) throws DepartmentBusinessException {
        return service.get(request);
    }

    @PutMapping("/department/add")
    public String add(HttpServletRequest request, HttpServletResponse response) throws DepartmentBusinessException {
        service.add(request);
        return "redirect:/http://localhost:8081/departments";
    }

    @PostMapping("/department/update")
    public String update(HttpServletRequest request, HttpServletResponse response) throws DepartmentBusinessException {
        service.update(request);
        return "redirect:/http://localhost:8081/departments";
    }

    @DeleteMapping("/department/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, DepartmentBusinessException {
        service.delete(request);
    }
}
