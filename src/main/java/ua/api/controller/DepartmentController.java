package ua.api.controller;

import ua.api.model.Department;
import ua.api.service.DepartmentService;
import ua.simpleservletframework.core.annotation.Autowired;
import ua.simpleservletframework.core.annotation.RequestBody;
import ua.simpleservletframework.mvc.annotation.annotation.controller.RestController;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.DeleteMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.GetMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.PostMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.PutMapping;
import ua.simpleservletframework.mvc.annotation.annotation.url.PathVariable;

import java.util.List;

@RestController("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService service;

    @GetMapping
    public List<Department> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Department get(@PathVariable("id") String id) {
        return service.get(id);
    }

    @PostMapping
    public Department add(@RequestBody Department departmentBody) {
        return service.add(departmentBody);
    }

    @PutMapping
    public Department update(@RequestBody Department departmentBody) {
        return service.update(departmentBody);
    }

    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable("id") String id) {
        return service.delete(id);
    }
}
