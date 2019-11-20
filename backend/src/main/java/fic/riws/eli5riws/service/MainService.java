package fic.riws.eli5riws.service;

import fic.riws.eli5riws.model.Answer;
import fic.riws.eli5riws.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> findAllAnswers() {
        return answerRepository.findAll();
    }

    public Iterable<Answer> saveAnswers(Iterable<Answer> answers) {
        return answerRepository.saveAll(answers);
    }

    public boolean existsByQuestionId(String questionId) {
        return !answerRepository.findByQuestionId(questionId).isEmpty();
    }

    public List<Answer> findAnswers(String text, String category) {
        return answerRepository.findAnswersByText(text);
    }
}
