package xyz.jguru.springelasticsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import xyz.jguru.springelasticsearch.model.Employee;
import xyz.jguru.springelasticsearch.repository.EmployeeRepository;

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
    public Page<Employee> findByName(@PathVariable("name") String name) {
        //return repository.findByName(name,null);
        return repository.findByNameContains(name,null);
    }



    @GetMapping("/organization/{organizationName}")
    public Page<Employee> findByOrganizationName(@PathVariable("organizationName") String organizationName) {
        return repository.findByOrganizationName(organizationName, null);
    }



}