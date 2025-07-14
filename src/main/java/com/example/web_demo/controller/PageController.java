package com.example.web_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller //处理页面
public class PageController {
    @GetMapping("/login")
    public String login() {
        // This will resolve to src/main/resources/templates/login.html
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }


}