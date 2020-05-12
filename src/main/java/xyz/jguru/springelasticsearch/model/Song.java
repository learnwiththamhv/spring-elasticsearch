package xyz.jguru.springelasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "sc_karaoke", type = "song")
public class Song {

    @Id
    private String id;

    @Field(type = FieldType.Object)
    private List<Author> authors = new ArrayList<>();

    @Field(type = FieldType.Object)
    private List<Category> categories = new ArrayList<>();

    private String content;

    private String unsigned_content;

    private String title;

    private String unsigned_title;

}
