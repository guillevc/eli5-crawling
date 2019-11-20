package fic.riws.eli5riws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    private String id;
    @Field(type = FieldType.Text, analyzer = "english")
    private String text;
    @Field(type = FieldType.Keyword)
    private String category;
    private Integer karma;

}
