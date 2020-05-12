package xyz.jguru.springelasticsearch.service;

import org.springframework.stereotype.Service;
import xyz.jguru.springelasticsearch.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

}
