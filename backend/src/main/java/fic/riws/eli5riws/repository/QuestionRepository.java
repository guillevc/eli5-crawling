package fic.riws.eli5riws.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import fic.riws.eli5riws.model.Question;

@Repository
public interface QuestionRepository extends ElasticsearchRepository<Question, String> {

    public List<Question> findAll();

    //public List<Question> findByCategoryAndTextLike(String category, String text);
}
