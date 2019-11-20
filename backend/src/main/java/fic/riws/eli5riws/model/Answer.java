package fic.riws.eli5riws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Document(indexName = "answer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String text;
    private Integer karma;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Question question;

}
