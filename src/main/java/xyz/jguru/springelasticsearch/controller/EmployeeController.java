package xyz.jguru.springelasticsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.jguru.springelasticsearch.model.Employee;
import xyz.jguru.springelasticsearch.repository.EmployeeRepository;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        return repository.save(employee);
    }

    @GetMapping("/{name}")
    public List<Employee> findByName(@PathVariable("name") String name) {
        return repository.findByName(name);
    }

    @GetMapping("/organization/{organizationName}")
    public List<Employee> findByOrganizationName(@PathVariable("organizationName") String organizationName) {
        return repository.findByOrganizationName(organizationName);
    }

}