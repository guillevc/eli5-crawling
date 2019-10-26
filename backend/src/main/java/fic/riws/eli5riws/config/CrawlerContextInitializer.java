package fic.riws.eli5riws.config;

import fic.riws.eli5riws.crawler.Crawler;
import fic.riws.eli5riws.service.AnswerService;
import fic.riws.eli5riws.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class CrawlerContextInitializer {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @PostConstruct
    public void init() {
        Crawler.setQuestionService(questionService);
        Crawler.setAnswerService(answerService);
    }

}