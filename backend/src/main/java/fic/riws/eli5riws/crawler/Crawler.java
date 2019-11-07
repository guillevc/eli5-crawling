package fic.riws.eli5riws.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import fic.riws.eli5riws.model.Answer;
import fic.riws.eli5riws.model.Question;
import fic.riws.eli5riws.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
public final class Crawler extends WebCrawler {
    
    private static QuestionService questionService;

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

    public static void setQuestionService(QuestionService questionService) {
        Crawler.questionService = questionService;
    }

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
        return (VALID_URLS.matcher(href).matches()) && (!FILTERS.matcher(href).matches()
            && !questionService.exists(href.substring(href.indexOf("comments/") + 9, href.indexOf("comments/") + 15)));
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        log.info("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData && url.contains("/comments/")) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();

            /* Obtenemos el html de la página y lo parseamos */
            String html = htmlParseData.getHtml();
            Document doc = Jsoup.parse(html);

            try{
                /* Para cada pregunta averigüamos el texto, categoría, karma y respuestas */
                Element questionTitleElement = doc.select("p.title a.title").first(); 
                Element questionCategoryElement = doc.select("p.title span.linkflairlabel").first();
                Element questionKarmaElement = doc.select(".score.unvoted").first();

                String questionText = questionTitleElement.text();
                String questionCategory = (questionCategoryElement != null &&  questionCategoryElement.hasText())? questionCategoryElement.text() : "Other";
                Integer questionKarma;

                try {
                    questionKarma = Integer.parseInt(questionKarmaElement.attr("title"));
                } catch (NumberFormatException e) {
                    questionKarma = null;
                }
                Question q = new Question(url.substring(url.indexOf("comments/") + 9, url.indexOf("comments/") + 15), 
                    questionText, questionCategory, questionKarma, new ArrayList<Answer>());
                // log.info("Question stored -> " + q.toString());
                List<Answer> answers = new ArrayList<Answer>();
                Elements questionResponses = doc.select(".commentarea > .sitetable > .comment:not(.stickied) > .entry");

                for (Element el : questionResponses) {
                    String answerHref = el.select("a.bylink").attr("href");

                    try {
                        String answerId = answerHref.substring(answerHref.length() - 8, answerHref.length() - 1);
                        String answerText = el.select(".usertext-body p").text();
                        if (!answerText.equals("[deleted]") && !answerText.equals("[removed]")) { 
                            Integer answerKarma;
                            try {
                                answerKarma = Integer.parseInt(el.select(".score.unvoted").attr("title"));
                            } catch (NumberFormatException e) {
                                answerKarma = null;
                            }
                            // log.info("Answer added!");
                            answers.add(new Answer(answerId, answerText, answerKarma, q.getId()));
                        }
                    } catch (StringIndexOutOfBoundsException e){
                        // log.info("Answer not added! " + answerHref);
                        continue;
                    }
                }
                q.setAnswers(answers);
                q = questionService.save(q);
                // log.info("Answers added!");
            }
            catch (Exception e){
                // log.info("Imposible to store!!!");
                e.printStackTrace();
            }
        }
    }
}