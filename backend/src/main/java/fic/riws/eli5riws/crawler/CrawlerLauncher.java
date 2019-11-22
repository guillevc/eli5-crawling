package fic.riws.eli5riws.crawler;

import com.google.common.io.Files;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class CrawlerLauncher {

    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = Files.createTempDir().getAbsolutePath();
        int numberOfCrawlers = 4;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxDepthOfCrawling(-1);
        config.setMaxPagesToFetch(2000);
        config.setUserAgentString("Subdesarrolladores");

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
        
        controller.startNonBlocking(factory, numberOfCrawlers);
        
        //controller.waitUntilFinish();
        controller.getCrawlersLocalData();

    }

}
