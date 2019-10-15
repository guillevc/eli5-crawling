package fic.riws.eli5riws.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Document(indexName = "eli5")
@Data
@NoArgsConstructor
public class Question {

    @Id
    private String id;
//    private String text;
//    private Integer karma;

}
