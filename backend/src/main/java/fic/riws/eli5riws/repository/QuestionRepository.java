package fic.riws.eli5riws.repository;

import fic.riws.eli5riws.model.Question;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface QuestionRepository extends ElasticsearchRepository<Question, String> {
}
