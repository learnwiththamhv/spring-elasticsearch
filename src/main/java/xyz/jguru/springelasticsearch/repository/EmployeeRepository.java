package xyz.jguru.springelasticsearch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import xyz.jguru.springelasticsearch.model.Employee;

public interface EmployeeRepository extends ElasticsearchRepository<Employee, Long> {

    Page<Employee> findByOrganizationName(String name, Pageable pageable);
    Page<Employee> findByName(String name, Pageable pageable);

    @Query("{ \"match_phrase\": { \"name\": \"?0\" }}")
    Page<Employee> findByNameHasWord(String word, Pageable pageable);

    Page<Employee> findByNameContains(String word,Pageable pageable);

}