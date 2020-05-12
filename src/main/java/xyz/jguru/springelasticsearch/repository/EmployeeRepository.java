package xyz.jguru.springelasticsearch.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.jguru.springelasticsearch.model.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByOrganizationName(String name);
    List<Employee> findByName(String name);

}