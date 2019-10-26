package fic.riws.eli5riws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fic.riws.eli5riws.model.Answer;
import fic.riws.eli5riws.repository.AnswerRepository;
/* import lombok.extern.slf4j.Slf4j;

@Slf4j */
@Service
public class AnswerService {

    @Autowired 
    private AnswerRepository answerRepository;

    /* public Answer save(Answer a){
        if (answerRepository.existsById(a.getId())) {
            log.info("Answer already stored");
            return a;
        }
        log.info("New answer stored");
        return answerRepository.save(a);
    } */

    public void saveAll(List <Answer> as){
        // log.info("Answers stored");
        answerRepository.saveAll(as);
    }

    /* public List<Answer> findAllByQuestionId(String questionId) {
        List <Answer> answers = new ArrayList<>();
        for(Answer a : answerRepository.findByQuestionId(questionId)) {
            answers.add(a);
        }
        return answers;
    } */
    
}
