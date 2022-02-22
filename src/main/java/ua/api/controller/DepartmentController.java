package ua.api.controller;

import ua.api.exceptions.DepartmentBusinessException;
import ua.api.model.entity.Department;
import ua.api.service.DepartmentService;
import ua.simpleservletframework.core.annotation.Autowired;
import ua.simpleservletframework.core.annotation.RequestBody;
import ua.simpleservletframework.mvc.annotation.annotation.controller.RestController;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.DeleteMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.GetMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.PostMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.PutMapping;
import ua.simpleservletframework.mvc.annotation.annotation.url.PathVariable;

import java.io.IOException;
import java.util.List;

import static ua.simpleservletframework.mvc.servlet.DispatcherServlet.request;

@RestController("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    @GetMapping
    public List<Department> findAll() throws DepartmentBusinessException {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Department get(@PathVariable("id") String id) throws DepartmentBusinessException {
        return service.get(id);
    }

    @PutMapping
    public String add() throws DepartmentBusinessException {
        service.add(request);
        return "redirect:/http://localhost:8081/departments";
    }

    @PostMapping
    public String update(@RequestBody Department departmentBody) {
        service.update(departmentBody);
        return "redirect:/http://localhost:8081/departments";
    }

    @DeleteMapping("/department/{id}")
    public void delete(@PathVariable("id") String id) throws IOException, DepartmentBusinessException {
        service.delete(id);
    }
}
