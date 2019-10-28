package fic.riws.eli5riws.model;

import javax.persistence.Id;

import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = "eli5-question", type = "question")
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
