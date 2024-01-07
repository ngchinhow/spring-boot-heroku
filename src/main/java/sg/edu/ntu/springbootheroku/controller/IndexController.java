package sg.edu.ntu.springbootheroku.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getIndex() {
        return ResponseEntity.ok("Spring Boot application connected to Heroku Postgres");
    }
}
