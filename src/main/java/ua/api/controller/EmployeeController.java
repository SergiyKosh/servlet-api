package ua.api.controller;

import ua.api.exceptions.EmployeeBusinessException;
import ua.api.model.Employee;
import ua.api.service.EmployeeService;
import ua.simpleservletframework.core.annotation.Autowired;
import ua.simpleservletframework.mvc.annotation.annotation.controller.RestController;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.DeleteMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.GetMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.PostMapping;
import ua.simpleservletframework.mvc.annotation.annotation.mapping.PutMapping;
import ua.simpleservletframework.mvc.annotation.annotation.url.PathVariable;

import java.util.List;

@RestController("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping
    public List<Employee> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable("id") String id) throws EmployeeBusinessException {
        return service.get(id);
    }

    @PutMapping
    public String save() {
        service.add();
        return "redirect:/http://localhost:8081/employees";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") String id) {
        service.update(id);
        return "redirect:/http://localhost:8081/employees";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) {
        service.delete(id);
        return "redirect:/http://localhost:8081/employees";
    }
}
