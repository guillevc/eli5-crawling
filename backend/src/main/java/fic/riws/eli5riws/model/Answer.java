package fic.riws.eli5riws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Document(indexName = "eli5-answer", type = "answer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    
    @Id
    private String id;
    private String text;
    private Integer karma;
    private String questionId;

}
