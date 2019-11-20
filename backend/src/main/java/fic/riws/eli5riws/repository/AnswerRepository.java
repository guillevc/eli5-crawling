package fic.riws.eli5riws.repository;

import fic.riws.eli5riws.model.Answer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends ElasticsearchRepository<Answer, String> {

    List<Answer> findAll();

    // exists nested no funciona
    //boolean existsByQuestionId(String questionId);
    // findFirst peta https://jira.spring.io/browse/DATAES-254
    List<Answer> findByQuestionId(String questionId);

    @Query("{\"query\": {\n" +
            "    \"bool\": {\n" +
            "        \"should\": {\n" +
            "            \"multi_match\": {\n" +
            "                \"query\": \"The universe is made up of atoms which are made out of subatomic particles which\",\n" +
            "                \"fields\": [\"text\", \"question.text\"]\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}\n" +
            "}")
    List<Answer> findAnswersByText(String text);

}
