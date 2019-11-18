package fic.riws.eli5riws.service;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.NestedSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import fic.riws.eli5riws.dto.RedditPost;
import fic.riws.eli5riws.model.Question;
import fic.riws.eli5riws.repository.QuestionRepository;

/* import lombok.extern.slf4j.Slf4j;

@Slf4j */
@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    public boolean exists(String s) {
        return questionRepository.existsById(s);
    }

    public Question save(Question q) {
        if (questionRepository.existsById(q.getId())) {
            // log.info("Question already stored");
            return q;
        }
        // log.info("New question stored");
        return questionRepository.save(q);
    }

    public List<RedditPost> findAll() {
        List<RedditPost> posts = new ArrayList<>();
        for (Question q : questionRepository.findAll()) {
            posts.add(new RedditPost(q));
        }
        return posts;
    }

    public Page<Question> findByTextAndCategory(String category, String text/* , Pageable config */) {
        NativeSearchQueryBuilder aux = new NativeSearchQueryBuilder()
            .withQuery(QueryBuilders.wildcardQuery("text", text))
            .withQuery(QueryBuilders.wildcardQuery("answers.text", text));

        if (!category.isEmpty()) {
            aux.withFilter(QueryBuilders.termQuery("category", category));
        }

        //aux.withSort(SortBuilders.scoreSort())
            aux.withSort(SortBuilders.fieldSort("answers.karma").order(SortOrder.DESC).setNestedSort(new NestedSortBuilder("answers")))
            .withSort(SortBuilders.fieldSort("karma").order(SortOrder.DESC));

        SearchQuery searchQuery = aux.build();
        
        Page<Question> questions = esTemplate.queryForPage(searchQuery, Question.class);
        
        return questions;
    }

}
