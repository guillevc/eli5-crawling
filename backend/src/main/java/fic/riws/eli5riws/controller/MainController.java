package fic.riws.eli5riws.controller;

import lombok.extern.slf4j.Slf4j;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import fic.riws.eli5riws.Crawler;

@RestController
@RequestMapping("${app.api.base-url}")
@Slf4j
public class MainController {

    @RequestMapping(path = "hello")
    public String sayHello() {
        log.info("GET called on /hello resource");
        return "hello";
    }

    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "/Users/luisin/data";
        int numberOfCrawlers = 4;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        // Instantiate the controller for this crawl.
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        robotstxtConfig.setEnabled(false);
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        // For each crawl, you need to add some seed urls. These are the first
        // URLs that are fetched and then the crawler starts following links
        // which are found in these pages
        controller.addSeed("https://old.reddit.com/r/explainlikeimfive/top/?sort=top&t=all");
    	
    	// The factory which creates instances of crawlers.
        CrawlController.WebCrawlerFactory<Crawler> factory = () -> new Crawler();
        
        // Start the crawl. This is a blocking operation, meaning that your code
        // will reach the line after this only when crawling is finished.
        controller.start(factory, numberOfCrawlers);

        List<Object> objects = controller.getCrawlersLocalData();
        
    }
}
