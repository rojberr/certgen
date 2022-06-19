package dev.drzymala.certgen.generator.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String index() {
        return "index";
    }
}
