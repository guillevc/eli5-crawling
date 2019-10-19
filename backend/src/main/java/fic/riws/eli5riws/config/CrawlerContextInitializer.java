package fic.riws.eli5riws.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import fic.riws.eli5riws.crawler.Crawler;
import fic.riws.eli5riws.service.QuestionService;

@Configuration
public class CrawlerContextInitializer {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private ApplicationContext context;

    @PostConstruct
    public void init() {
        Crawler.setQuestionsService(questionService);
    }
}