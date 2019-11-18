package fic.riws.eli5riws.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fic.riws.eli5riws.model.Question;

@Repository
public interface QuestionRepository extends ElasticsearchRepository<Question, String> {

	public List<Question> findAll();
	
	public Page<Question> findByText(String text, Pageable config);

    @Query("{\n" +
            "    \"bool\": {\n" +
            "        \"must\": {\n" +
            "            \"match\": {\n" +
            "                \"text\": \"colour\"\n" +
            "            }\n" +
            "        }\n" +
            "    }")
    public List<Question> findByText(String text);

    /* @Query("{\n" +
            "    \"bool\": {\n" +
            "        \"must\": {\n" +
            "            \"match\": {\n" +
            "                \"text\": \"colour\"\n" +
            "            }\n" +
            "        }\n" +
            "    }")
    public List<Question> findByCategory(String category); */

}
