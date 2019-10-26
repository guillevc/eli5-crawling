package fic.riws.eli5riws.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import fic.riws.eli5riws.model.Answer;

@Repository
public interface AnswerRepository extends ElasticsearchRepository<Answer, String> {

    public List<Answer> findAll();

    public List<Answer> findByQuestionId(String questionId);
}
