package fic.riws.eli5riws.service;

import fic.riws.eli5riws.model.Answer;
import fic.riws.eli5riws.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Page<Answer> findAnswers(String text, Optional<String> category, Pageable pageable) {
        if (category.isPresent()) {
            return answerRepository.customFindAnswersByTextAndCategory(text, category.get(), pageable);
        } else {
            return answerRepository.customFindAnswersByText(text, pageable);
        }
    }
}
