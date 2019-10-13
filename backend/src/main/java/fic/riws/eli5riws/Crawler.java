package fic.riws.eli5riws;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import fic.riws.eli5riws.dto.RedditPost;
import fic.riws.eli5riws.dto.RedditResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Crawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(" +
            "css|js" +
            "|bmp|gif|jpe?g|JPE?G|png|tiff?|ico|nef|raw" +
            "|mid|mp2|mp3|mp4|wav|wma|flv|mpe?g" +
            "|avi|mov|mpeg|ram|m4v|wmv|rm|smil" +
            "|pdf|doc|docx|pub|xls|xlsx|vsd|ppt|pptx" +
            "|swf" +
            "|zip|rar|gz|bz2|7z|bin" +
            "|xml|txt|java|c|cpp|exe" +
            "))$");

    private final static Pattern VALID_URLS = Pattern.compile("https://old.reddit.com/r/explainlikeimfive/comments/\\w{6}/eli5.[^/]*/$" +
        "|https://old.reddit.com/r/explainlikeimfive/top/\\?sort=top&t=all.*");

    /**
     * Creates a new crawler instance.
     */
    public Crawler() {
    }

    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "https://www.reddit.com/r/explainlikeimfive/top/?t=all". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
     @Override
     public boolean shouldVisit(Page referringPage, WebURL url) {
         String href = url.getURL().toLowerCase();
         return (VALID_URLS.matcher(href).matches())&& (!FILTERS.matcher(href).matches());  
     }

     /**
      * This function is called when a page is fetched and ready
      * to be processed by your program.
      */
     @Override
     public void visit(Page page) {
		String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData 
                && url.contains("/comments/")) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            //String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            //Set<WebURL> links = htmlParseData.getOutgoingUrls();

            String category;
            String question;
            List<RedditResponse> responses = new ArrayList<>();

            Document doc = Jsoup.parse(html);

            Element questionTitleElement = doc.select("p.title a.title").first();
            question = questionTitleElement.text();

            Element questionCategoryElement = doc.select("p.title span.linkflairlabel").first();
            category = questionCategoryElement.text();

            Elements questionResponses = doc.select(".commentarea > .sitetable > .comment > .entry");
            for (Element el : questionResponses) {
                String text = el.select(".usertext-body p").text();
                if (!text.equals("[deleted]") && !text.equals("[removed]")) {
                    Integer karma;
                    try {
                        karma = Integer.parseInt(el.select(".score.unvoted").attr("title"));
                    } catch (NumberFormatException e) {
                        karma = null;
                    }
                    responses.add(new RedditResponse(text, karma));
                }
            }

            RedditPost redditPost = new RedditPost(category, question, responses, 0); // todo: karma del post
            System.out.println(redditPost);
        }
    }
}