package fic.riws.eli5riws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "answer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    
    @Id
    private String id;
    @Field(type = FieldType.Text, analyzer = "english")
    private String text;
    private Integer karma;
    private String url;
    @Field(type = FieldType.Nested, includeInParent = true)
    private Question question;

}
