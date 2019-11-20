package fic.riws.eli5riws.config;

import fic.riws.eli5riws.crawler.Crawler;
import fic.riws.eli5riws.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class CrawlerContextInitializer {

    @Autowired
    private MainService mainService;


    @PostConstruct
    public void init() {
        Crawler.setMainService(mainService);
    }

}