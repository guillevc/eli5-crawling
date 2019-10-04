package fic.riws.eli5riws.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${app.api.base-url}")
@Slf4j
public class MainController {

    @RequestMapping(path = "hello")
    public String sayHello() {
        log.info("GET called on /hello resource");
        return "hello";
    }

}
