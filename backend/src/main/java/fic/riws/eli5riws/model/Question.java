package fic.riws.eli5riws.model;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;

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
    private String text;
    private String category;
    private Integer karma;

}
