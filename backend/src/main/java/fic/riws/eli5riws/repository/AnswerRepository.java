package fic.riws.eli5riws.repository;

import fic.riws.eli5riws.model.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface AnswerRepository extends ElasticsearchRepository<Answer, String> {

        List<Answer> findAll();

        // exists nested no funciona
        // boolean existsByQuestionId(String questionId);
        // findFirst peta https://jira.spring.io/browse/DATAES-254-
        List<Answer> findByQuestionId(String questionId);

        @Query("{\"function_score\":{\"query\":{\"bool\":{\"must\":{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"text^2\",\"question.text\"],\"type\":\"most_fields\"}},\"filter\":[{\"term\":{\"question.category\":\"?1\"}}]}},\"functions\":[{\"field_value_factor\":{\"field\":\"karma\",\"factor\":0.1,\"modifier\":\"square\",\"missing\":1}}]}}")
        Page<Answer> customFindAnswersByTextAndCategory(String text, String category, Pageable pageable);

        @Query("{\"function_score\":{\"query\":{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"text^2\",\"question.text\"],\"type\":\"most_fields\"}},\"functions\":[{\"field_value_factor\":{\"field\":\"karma\",\"factor\":0.1,\"modifier\":\"square\",\"missing\":1}}]}}")
        Page<Answer> customFindAnswersByText(String text, Pageable pageable);

}
