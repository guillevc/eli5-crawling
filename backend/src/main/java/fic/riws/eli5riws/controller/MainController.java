package fic.riws.eli5riws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fic.riws.eli5riws.crawler.CrawlerLauncher;
import fic.riws.eli5riws.dto.RedditPost;
import fic.riws.eli5riws.model.Question;
import fic.riws.eli5riws.service.QuestionService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("${app.api.base-url}")
@Slf4j
public class MainController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(path = "hello")
    public String sayHello() {
        log.info("GET called on /hello resource");
        return "hello";
    }

    @RequestMapping(path = "index")
    public void index() throws Exception {
        CrawlerLauncher.main(null);
    }

    @RequestMapping(path = "list")
    public Page<Question> list(@RequestParam(required = false) String category, @RequestParam String text) {
        if (category != null && !category.isEmpty())
            return questionService.findByTextAndCategory(category, text);
        else
            return questionService.findByTextAndCategory("", text);
    }

}
