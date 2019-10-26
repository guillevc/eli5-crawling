package fic.riws.eli5riws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fic.riws.eli5riws.dto.RedditPost;
import fic.riws.eli5riws.model.Answer;
import fic.riws.eli5riws.model.Question;
import fic.riws.eli5riws.repository.AnswerRepository;
import fic.riws.eli5riws.repository.QuestionRepository;
/* import lombok.extern.slf4j.Slf4j;

@Slf4j */
@Service
public class QuestionService {

    @Autowired 
    private QuestionRepository questionRepository;

    @Autowired 
    private AnswerRepository answerRepository;

    public boolean exists(String s) {
        return questionRepository.existsById(s);
    }

    public Question save(Question q){
        if (questionRepository.existsById(q.getId())) {
            // log.info("Question already stored");
            return q;
        }
        // log.info("New question stored");
        return questionRepository.save(q);
    }

    public List<RedditPost> findAll() {
        List <RedditPost> posts = new ArrayList<>();
        for(Question q : questionRepository.findAll()) {
            List<Answer> answers = answerRepository.findByQuestionId(q.getId());
            posts.add(new RedditPost(q, answers));
        }
        return posts;
    }
    
}
