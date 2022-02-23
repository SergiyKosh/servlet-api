package ua.api.controller;

import ua.api.model.Employee;
import ua.api.service.EmployeeService;
import ua.api.service.Service;
import ua.simpleservletframework.core.annotation.Autowired;
import ua.simpleservletframework.core.annotation.RequestBody;
import ua.simpleservletframework.mvc.annotation.annotation.controller.RestController;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.DeleteMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.GetMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.PostMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.PutMapping;
import ua.simpleservletframework.mvc.annotation.annotation.url.PathVariable;

import java.util.List;

@RestController("/employees")
public class EmployeeController {
    @Autowired("employeeService")
    private Service<Employee> service;

    @GetMapping
    public List<Employee> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable("id") String id) {
        return service.get(id);
    }

    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        return service.add(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@RequestBody Employee employee) {
        return service.update(employee);
    }

    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable("id") String id) {
        return service.delete(id);
    }
}
