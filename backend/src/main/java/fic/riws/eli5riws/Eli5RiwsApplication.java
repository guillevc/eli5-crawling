package fic.riws.eli5riws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import fic.riws.eli5riws.service.QuestionService;

@SpringBootApplication
public class Eli5RiwsApplication {

	@Autowired
    private static QuestionService questionService;

    private static ApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(Eli5RiwsApplication.class, args);
        questionService = (QuestionService) context.getBean("questionService");
	}

}
