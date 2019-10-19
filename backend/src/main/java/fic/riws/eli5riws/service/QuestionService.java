package fic.riws.eli5riws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fic.riws.eli5riws.dto.RedditPost;
import fic.riws.eli5riws.dto.RedditResponse;
import fic.riws.eli5riws.model.Question;
import fic.riws.eli5riws.repository.QuestionRepository;

@Service
public class QuestionService {

    @Autowired 
    private QuestionRepository questionRepository;

    public Question save(Question q){
        return questionRepository.save(q);
    }

    public void test() {
        questionRepository.save(new Question("123456", "Texttexttext asdlfajskdf sdjlfks d", "Categoryaskjdhaks"));
    }

    public List<RedditPost> findAllQuestions() {
        List <RedditPost> posts = new ArrayList<>();
        for(Question q : questionRepository.findAll()) {
            posts.add(new RedditPost(q.getCategory(), q.getText(), new ArrayList<RedditResponse>(),123));
        }
        return posts;
    }
    
}
