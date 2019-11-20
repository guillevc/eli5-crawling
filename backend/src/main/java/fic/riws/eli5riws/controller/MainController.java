package fic.riws.eli5riws.controller;

import fic.riws.eli5riws.crawler.CrawlerLauncher;
import fic.riws.eli5riws.model.Answer;
import fic.riws.eli5riws.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${app.api.base-url}")
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping(path = "index")
    public void index() throws Exception {
        CrawlerLauncher.main(null);
    }

    @RequestMapping(path = "answers/all")
    public List<Answer> list() {
        return mainService.findAllAnswers();
    }

    @RequestMapping(path = "answers")
    public Page<Answer> list(@RequestParam String text, @RequestParam(required = false) String category, Pageable pageable) {
        Optional<String> categoryOptional;
        if (category == null || category.trim().equals("")) {
            categoryOptional = Optional.ofNullable(null);
        } else {
            categoryOptional = Optional.ofNullable(category);
        }
        return mainService.findAnswers(text, categoryOptional, pageable);
    }

}
