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

        @Query("{\n" +
                "        \"function_score\": {\n" +
                "            \"query\": {\n" +
                "                \"bool\": {\n" +
                "                    \"must\": {\n" +
                "                        \"multi_match\": {\n" +
                "                            \"query\": \"?0\",\n" +
                "                            \"fields\": [\n" +
                "                                \"text^2\",\n" +
                "                                \"question.text\"\n" +
                "                            ],\n" +
                "                            \"type\": \"most_fields\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    \"filter\": [\n" +
                "                        {\n" +
                "                            \"term\": {\n" +
                "                                \"question.category\": \"?1\"\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            },\n" +
                "            \"functions\": [\n" +
                "                {\n" +
                "                    \"field_value_factor\": {\n" +
                "                        \"field\": \"karma\",\n" +
                "                        \"factor\": 0.1,\n" +
                "                        \"modifier\": \"sqrt\",\n" +
                "                        \"missing\": 1\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"boost_mode\": \"sum\"\n" +
                "        }\n" +
                "    }")
        Page<Answer> customFindAnswersByTextAndCategory(String text, String category, Pageable pageable);

        @Query("{\n" +
                "        \"function_score\": {\n" +
                "            \"query\": {\n" +
                "                \"multi_match\": {\n" +
                "                    \"query\": \"?0\",\n" +
                "                    \"fields\": [\n" +
                "                        \"text^2\",\n" +
                "                        \"question.text\"\n" +
                "                    ],\n" +
                "                    \"type\": \"most_fields\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"functions\": [\n" +
                "                {\n" +
                "                    \"field_value_factor\": {\n" +
                "                        \"field\": \"karma\",\n" +
                "                        \"factor\": 0.1,\n" +
                "                        \"modifier\": \"sqrt\",\n" +
                "                        \"missing\": 1\n" +
                "                    }\n" +
                "                }\n" +
                "            ],\n" +
                "            \"boost_mode\": \"sum\"\n" +
                "        }\n" +
                "    }")
        Page<Answer> customFindAnswersByText(String text, Pageable pageable);

}
