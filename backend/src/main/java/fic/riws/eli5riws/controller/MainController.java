package fic.riws.eli5riws.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${app.api.base-url}")
public class MainController {

    @RequestMapping(path = "hello")
    public String sayHello() {
//        LOG.info("GET called on /hello resource");
        return "hello";
    }

}
