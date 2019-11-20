package fic.riws.eli5riws.controller;

import fic.riws.eli5riws.crawler.CrawlerLauncher;
import fic.riws.eli5riws.model.Answer;
import fic.riws.eli5riws.model.Question;
import fic.riws.eli5riws.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${app.api.base-url}")
@Slf4j
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping(path = "hello")
    public String sayHello() {
        log.info("GET called on /hello resource");
        return "hello";
    }

    @RequestMapping(path = "index")
    public void index() throws Exception {
        CrawlerLauncher.main(null);
    }

    @RequestMapping(path = "answers/all")
    public List<Answer> list() {
        return mainService.findAllAnswers();
    }

    @RequestMapping(path = "answers")
    public List<Answer> list(@RequestParam String text, @RequestParam(required = false) String category) {
        return mainService.findAnswers(text, category);
    }

}
