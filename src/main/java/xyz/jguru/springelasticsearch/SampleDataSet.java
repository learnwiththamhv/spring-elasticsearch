package xyz.jguru.springelasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import xyz.jguru.springelasticsearch.model.Author;
import xyz.jguru.springelasticsearch.model.Category;
import xyz.jguru.springelasticsearch.model.Song;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SampleDataSet {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleDataSet.class);
    //private static final String INDEX_NAME = "sample";
    private static final String INDEX_NAME = "sc_karaoke";
    //private static final String INDEX_TYPE = "employee";
    private static final String INDEX_TYPE = "song";
    private static int COUNTER = 0;

    @Autowired
    ElasticsearchTemplate template;
    @Autowired
    TaskExecutor taskExecutor;

    @PostConstruct
    public void init() {
        if (!template.indexExists(INDEX_NAME)) {
            template.createIndex(INDEX_NAME);
            LOGGER.info("New index created: {}", INDEX_NAME);
        }
        for (int i = 0; i < 10000; i++) {
            taskExecutor.execute(() -> bulk());
        }
    }

//    public void bulk() {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            List<IndexQuery> queries = new ArrayList<>();
//            List<Employee> employees = employees();
//            for (Employee employee : employees) {
//                IndexQuery indexQuery = new IndexQuery();
//                indexQuery.setSource(mapper.writeValueAsString(employee));
//                indexQuery.setIndexName(INDEX_NAME);
//                indexQuery.setType(INDEX_TYPE);
//                queries.add(indexQuery);
//            }
//            if (queries.size() > 0) {
//                template.bulkIndex(queries);
//            }
//            template.refresh(INDEX_NAME);
//            LOGGER.info("BulkIndex completed: {}", ++COUNTER);
//        } catch (Exception e) {
//            LOGGER.error("Error bulk index", e);
//        }
//    }
//
//    private List<Employee> employees() {
//        List<Employee> employees = new ArrayList<>();
//        for (int i = 0; i < 10000; i++) {
//            Random r = new Random();
//            Employee employee = new Employee();
//            employee.setName("JohnSmith" + r.nextInt(1000000));
//            employee.setAge(r.nextInt(100));
//            employee.setPosition("Developer");
//            int departmentId = r.nextInt(500000);
//            employee.setDepartment(new Department((long) departmentId, "TestD" + departmentId));
//            int organizationId = departmentId / 100;
//            employee.setOrganization(new Organization((long) organizationId, "TestO" + organizationId, "Test Street No. " + organizationId));
//            employees.add(employee);
//        }
//        return employees;
//    }


    public void bulk() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<IndexQuery> queries = new ArrayList<>();
            List<Song> songs = songs();
            for (Song song : songs) {
                IndexQuery indexQuery = new IndexQuery();
                indexQuery.setSource(mapper.writeValueAsString(song));
                indexQuery.setIndexName(INDEX_NAME);
                indexQuery.setType(INDEX_TYPE);
                queries.add(indexQuery);
            }
            if (queries.size() > 0) {
                template.bulkIndex(queries);
            }
            template.refresh(INDEX_NAME);
            LOGGER.info("BulkIndex completed: {}", ++COUNTER);
        } catch (Exception e) {
            LOGGER.error("Error bulk index", e);
        }
    }

    private List<Song> songs() {
        List<Song> songs = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Random r = new Random();
            Song song = new Song();
            song.setTitle("Bai Hat " + r.nextInt(1000000));
            song.setContent("Mua van mua bay" + r.nextInt(1000000));

            Long authorId, catId;
            Author author = new Author();
            Category category = new Category();

            for(int j=0; j<r.nextInt(5);i++){
                authorId = r.nextLong();
                author = new Author(authorId,"Author" + r.nextInt(1000000));

                catId = r.nextLong();
                category = new Category(catId,"Category"+ r.nextInt(1000000));
                song.getAuthors().add(author);
                song.getCategories().add(category);

            }

            song.setContent("content noi dung bai hat " + r.nextInt(1000000));

            songs.add(song);

        }
        return songs;
    }

}